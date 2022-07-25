package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private float exeTime;
    private boolean repeat;
    private boolean firstExe;

    public Animation(TextureRegion region, int frameCount, float cycleTime, boolean repeat){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
        this.repeat = repeat;
        firstExe = true;
        exeTime = cycleTime * frameCount;
    }

    public void update(float dt){
        if(firstExe) {
            currentFrameTime += dt;
            if(currentFrameTime > maxFrameTime){
                frame++;
                currentFrameTime = 0;
            }
            if(frame >= frameCount)
                frame = 0;
                if(!repeat)
                    firstExe = false;
        }
    }

    public void flip(){
        for(TextureRegion region : frames)
            region.flip(true, false);
    }

    public void reset() {
        frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}