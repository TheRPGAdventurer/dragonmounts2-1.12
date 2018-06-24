package com.TheRPGAdventurer.ROTD.client.sound;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.EnumDragonLifeStage;
import com.TheRPGAdventurer.ROTD.util.math.MathX;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

/**
 * Created by TheGreyGhost on 8/10/14.
 *
 * Used to create sound effects for the breath weapon tool - start up, sustained loop, and wind-down
 * The sound made by the dragon's head
 *   1) initial startup
 *   2) looping while breathing
 *   3) stopping when done
 *  Sometimes the sound doesn't layer properly on the first try.  I don't know why.  I have implemented a preload
 *    which seems to help.
 *
 * The SoundEffectBreathWeapon corresponds to the breath weapon of a single dragon.  Typical usage is:
 * 1) create an instance, and provide a callback function (WeaponSoundUpdateLink)
 * 2) startPlaying(), startPlayingIfNotAlreadyPlaying(), stopPlaying() to start or stop the sounds completely
 * 3) once per tick, call performTick().
 *   3a) performTick() will call the WeaponSoundUpdateLink.refreshWeaponSoundInfo(), which should return the
 *       current data relevant to the sound (eg whether the dragon is breathing, and the location of the beam)
 *
 * Is intended to be subclassed for future different breath weapons.
 *
 */
public class SoundEffectBreathWeapon {
	
	  WeaponSoundInfo.State currentWeaponState = WeaponSoundInfo.State.IDLE;

  private ComponentSoundSettings headSoundSettings = new ComponentSoundSettings(1.0F);

  private BreathWeaponSound headStartupSound;
  private BreathWeaponSound headLoopSound;
  private BreathWeaponSound headStoppingSound;

  private int ticksElapsed;
  private SoundController soundController;
  private WeaponSoundUpdateLink weaponSoundUpdateLink;
	  
  public SoundEffectBreathWeapon(SoundController i_soundController, WeaponSoundUpdateLink i_weaponSoundUpdateLink) {
    soundController = i_soundController;
    weaponSoundUpdateLink = i_weaponSoundUpdateLink;
  }

  private final float HEAD_MIN_VOLUME = 0.02F;

  public void startPlaying(EntityPlayerSP entityPlayerSP, EntityTameableDragon dragon) {
    stopAllSounds();
    currentWeaponState = WeaponSoundInfo.State.IDLE;
    performTick(entityPlayerSP, dragon);
  }

  public void stopPlaying() {
    stopAllSounds();
  }

  private void stopAllSounds() {
    stopAllHeadSounds();
  }

  private void stopAllHeadSounds() {
    if (headStartupSound != null) {
      soundController.stopSound(headStartupSound);
      headStartupSound = null;
    }
    if (headLoopSound != null) {
      soundController.stopSound(headLoopSound);
      headLoopSound = null;
    }

    if (headStoppingSound != null) {
      soundController.stopSound(headStoppingSound);
      headStoppingSound = null;
    }
  }


  private void setAllStopFlags() {
    if (headStartupSound != null) { headStartupSound.setDonePlaying();}
    if (headLoopSound != null) { headLoopSound.setDonePlaying();}
    if (headStoppingSound != null) { headStoppingSound.setDonePlaying();}
  }

  /**
   * Updates all the component sounds according to the state of the breath weapon.
   * @param entityPlayerSP
   */
  public void performTick(EntityPlayerSP entityPlayerSP, EntityTameableDragon dragon) {
    ++ticksElapsed;
    WeaponSoundInfo weaponSoundInfo = new WeaponSoundInfo();
    boolean keepPlaying = weaponSoundUpdateLink.refreshWeaponSoundInfo(weaponSoundInfo);
    if (!keepPlaying) {
      setAllStopFlags();
      return;
    }
    checkNotNull(weaponSoundInfo.dragonHeadLocation);
    headSoundSettings.playing = true;
    headSoundSettings.masterVolume = weaponSoundInfo.relativeVolume;
    headSoundSettings.soundEpicentre = weaponSoundInfo.dragonHeadLocation;

    headSoundSettings.playerDistanceToEpicentre =
              (float) weaponSoundInfo.dragonHeadLocation.distanceTo(entityPlayerSP.getPositionVector());

    final int HEAD_STARTUP_TICKS = 40;
    final int HEAD_STOPPING_TICKS = 60;

    // if state has changed, stop and start component sounds appropriately

    if (weaponSoundInfo.breathingState != currentWeaponState) {
      switch (weaponSoundInfo.breathingState) {
        case IDLE: {
//          breathingStopTick = ticksElapsed;
          stopAllHeadSounds();
          headStoppingSound = new BreathWeaponSound(weaponSound(SoundPart.STOP, weaponSoundInfo.lifeStage, dragon), 
                                                    HEAD_MIN_VOLUME, RepeatType.NO_REPEAT,
                                                    headSoundSettings);
          headStoppingSound.setPlayCountdown(HEAD_STOPPING_TICKS);
          soundController.playSound(headStoppingSound);
          break;
        }
        case BREATHING: {
//          breathingStartTick = ticksElapsed;
          stopAllHeadSounds();
          BreathWeaponSound preloadLoop = new BreathWeaponSound(weaponSound(SoundPart.LOOP, weaponSoundInfo.lifeStage, dragon), 
                                                                Mode.PRELOAD);
          soundController.playSound(preloadLoop);
          BreathWeaponSound preLoadStop = new BreathWeaponSound(weaponSound(SoundPart.STOP, weaponSoundInfo.lifeStage, dragon),
                  Mode.PRELOAD);
          soundController.playSound(preLoadStop);
          headStartupSound = new BreathWeaponSound(weaponSound(SoundPart.START, weaponSoundInfo.lifeStage, dragon), 
                                                   HEAD_MIN_VOLUME, RepeatType.NO_REPEAT,
                                                   headSoundSettings);
          headStartupSound.setPlayCountdown(HEAD_STARTUP_TICKS);
          soundController.playSound(headStartupSound);
          break;
        }
        default: {
          System.err.printf("Illegal weaponSoundInfo.breathingState:" + weaponSoundInfo.breathingState + " in " + this
                  .getClass());
        }
      }
      currentWeaponState = weaponSoundInfo.breathingState;
    }

    // update component sound settings based on weapon info and elapsed time

    switch (currentWeaponState) {
      case BREATHING: {
        if (headStartupSound != null && headStartupSound.getPlayCountdown() <= 0) {
          stopAllHeadSounds();
          headLoopSound = new BreathWeaponSound(weaponSound(SoundPart.LOOP, weaponSoundInfo.lifeStage, dragon), 
                                                HEAD_MIN_VOLUME, RepeatType.REPEAT, headSoundSettings);
          soundController.playSound(headLoopSound);
        }

        break;
      }
      case IDLE: {
        if (headStoppingSound != null) {
          if (headStoppingSound.getPlayCountdown() <= 0) {   //|| !soundController.isSoundPlaying(headStoppingSound)) {  causes strange bug "channel null in method 'stop'"
            soundController.stopSound(headStoppingSound);
            headStoppingSound = null;
          }
        }
        break;
      }
      default: {
        System.err.printf("Unknown currentWeaponState:" + currentWeaponState);
      }
    }
  }

  /**
   * Used as a callback to update the sound's position and
   */
  public interface WeaponSoundUpdateLink {
    boolean refreshWeaponSoundInfo(WeaponSoundInfo infoToUpdate);
  }

  public static class WeaponSoundInfo {
    public enum State {IDLE, BREATHING}
    public State breathingState = State.IDLE;
    public Collection<Vec3d> pointsWithinBeam;
    public Vec3d dragonHeadLocation;
    public float relativeVolume; // 0 to 1
    public EnumDragonLifeStage lifeStage;
  }

  // settings for each component sound
  private static class ComponentSoundSettings {
    public ComponentSoundSettings(float i_volume) {
      masterVolume = i_volume;
    }
    public float masterVolume;  // multiplier for the volume = 0 .. 1
    public Vec3d soundEpicentre;
    public float playerDistanceToEpicentre;
    public boolean playing;
  }

  public enum RepeatType {REPEAT, NO_REPEAT}
  public enum Mode {PRELOAD, PLAY}

  private class BreathWeaponSound extends PositionedSound implements ITickableSound {
    public BreathWeaponSound(ResourceLocation i_resourceLocation, float i_volume, RepeatType i_repeat,
                             ComponentSoundSettings i_soundSettings) {
      super(i_resourceLocation, SoundCategory.VOICE);
      repeat = (i_repeat == RepeatType.REPEAT);
      volume = i_volume;
      attenuationType = AttenuationType.NONE;
      soundSettings = i_soundSettings;
      playMode = Mode.PLAY;
    }

    /**
     * Preload for this sound (plays at very low volume).
     * Can't be a static method because that's not allowed in inner class
     * @param i_resourceLocation the sound to be played
     * @param mode dummy argument.  Must always be PRELOAD
     */
    public BreathWeaponSound(ResourceLocation i_resourceLocation, Mode mode) {
      super(i_resourceLocation, SoundCategory.VOICE);
      checkArgument(mode == Mode.PRELOAD);
      repeat = false;
      final float VERY_LOW_VOLUME = 0.001F;
      volume = VERY_LOW_VOLUME;
      attenuationType = AttenuationType.NONE;
      soundSettings = null;
      playMode = Mode.PRELOAD;
      preloadTimeCountDown = 5;  // play for a few ticks only
    }

    private void setDonePlaying() {
      donePlaying = true;
    }

    private boolean donePlaying;
    private ComponentSoundSettings soundSettings;
    private Mode playMode;

    public int getPlayCountdown() {
      return playTimeCountDown;
    }

    public void setPlayCountdown(int countdown) {
      playTimeCountDown = countdown;
    }

    private int playTimeCountDown = -1;
    private int preloadTimeCountDown = 0;


    @Override
    public boolean isDonePlaying() {
      return donePlaying;
    }

    @Override
    public void update() {
      final float MINIMUM_VOLUME = 0.10F;
      final float MAXIMUM_VOLUME = 1.00F;
      final float INSIDE_VOLUME = 1.00F;
      final float OFF_VOLUME = 0.0F;

      if (playMode == Mode.PRELOAD) {
        if (--preloadTimeCountDown <= 0) {
          this.volume = OFF_VOLUME;
        }
        return;
      }

      --playTimeCountDown;
      if (!soundSettings.playing) {
        this.volume = OFF_VOLUME;
      } else {
        this.xPosF = (float)soundSettings.soundEpicentre.x;
        this.yPosF = (float)soundSettings.soundEpicentre.y;
        this.zPosF = (float)soundSettings.soundEpicentre.z;
        if (soundSettings.playerDistanceToEpicentre < 0.01F) {
          this.volume = INSIDE_VOLUME;
        } else {
          final float MINIMUM_VOLUME_DISTANCE = 40.0F;
          float fractionToMinimum = soundSettings.playerDistanceToEpicentre / MINIMUM_VOLUME_DISTANCE;
          this.volume = soundSettings.masterVolume *
                        MathX.clamp(MAXIMUM_VOLUME - fractionToMinimum * (MAXIMUM_VOLUME - MINIMUM_VOLUME),
                                    MINIMUM_VOLUME, MAXIMUM_VOLUME);
        }
      }
    }
  }

  protected enum SoundPart {START, LOOP, STOP}

  /**
   * Returns the sound for the given breed, lifestage, and sound part 
   * @param soundPart which part of the breathing sound?
   * @param lifeStage how old is the dragon?
   * @return the resourcelocation corresponding to the desired sound
   */
  protected ResourceLocation weaponSound(SoundPart soundPart, EnumDragonLifeStage lifeStage, EntityTameableDragon dragon) {
    final SoundEffectNames hatchlingfire[] = {SoundEffectNames.HATCHLING_BREATHE_FIRE_START,
                                          SoundEffectNames.HATCHLING_BREATHE_FIRE_LOOP,
                                          SoundEffectNames.HATCHLING_BREATHE_FIRE_STOP};

    final SoundEffectNames juvenilefire[] = {SoundEffectNames.JUVENILE_BREATHE_FIRE_START,
                                          SoundEffectNames.JUVENILE_BREATHE_FIRE_LOOP,
                                          SoundEffectNames.JUVENILE_BREATHE_FIRE_STOP};

    final SoundEffectNames adultfire[] = {SoundEffectNames.ADULT_BREATHE_FIRE_START,
                                      SoundEffectNames.ADULT_BREATHE_FIRE_LOOP,
                                      SoundEffectNames.ADULT_BREATHE_FIRE_STOP};
    
    final SoundEffectNames hatchlingice[] = {SoundEffectNames.HATCHLING_BREATHE_ICE_START,
            SoundEffectNames.HATCHLING_BREATHE_ICE_LOOP,
            SoundEffectNames.HATCHLING_BREATHE_ICE_STOP};

final SoundEffectNames juvenileice[] = {SoundEffectNames.JUVENILE_BREATHE_ICE_START,
            SoundEffectNames.JUVENILE_BREATHE_ICE_LOOP,
            SoundEffectNames.JUVENILE_BREATHE_ICE_STOP};

final SoundEffectNames adultice[] = {SoundEffectNames.ADULT_BREATHE_ICE_START,
        SoundEffectNames.ADULT_BREATHE_ICE_LOOP,
        SoundEffectNames.ADULT_BREATHE_ICE_STOP};

    SoundEffectNames [] soundEffectNames;
    if(dragon.getBreed().useColdSound()) {
    	soundEffectNames = hatchlingice;
    } else {
    switch (lifeStage) {
      case HATCHLING: {
    	//if(dragon.getBreed().useColdSound()) {
    		soundEffectNames = hatchlingice;
    //	} else {
       //    soundEffectNames = hatchlingfire;
    //	}
        break;
      }
      case JUVENILE: {
    	//  if(dragon.getBreed().useColdSound()) {
    	//	  soundEffectNames = juvenileice;
    	//  } else {
            soundEffectNames = juvenilefire;
    	//  }
        break;
      }
      case ADULT: {
    	//  if(dragon.getBreed().useColdSound()) {
        //    soundEffectNames = adultfire;
    	//  } else {
    		  soundEffectNames = adultice;
    	//  }
        break;
      }
      default: {
        System.err.println("Unknown lifestage:" + lifeStage + " in weaponSound()");
    //    if(dragon.getBreed().useColdSound()) { // dummy
       //     soundEffectNames = adultfire;
    	//  } else {
    		  soundEffectNames = adultice;
    	//  }
        }
      }
    }
    return new ResourceLocation(soundEffectNames[soundPart.ordinal()].getJsonName());
  }


}
