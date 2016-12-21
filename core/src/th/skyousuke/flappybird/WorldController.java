/*
 * Copyright 2016 Surasek Nusati <surasek@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.skyousuke.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;


public class WorldController implements BirdListener {

    private CameraHelper cameraHelper;
    private ScrollingFloor scrollingFloor;
    private Background background;
    private Array<Pipe> pipes;
    private PipeGenerator pipeGenerator;
    private Bird bird;

    private boolean birdHitPipe;
    private boolean gameStart;
    private boolean gameOver;

    private WorldListener listener;

    public WorldController() {
        init();
    }

    private void init() {
        cameraHelper = new CameraHelper();
        scrollingFloor = new ScrollingFloor();
        background = new Background();
        pipes = new Array<>();
        pipeGenerator = new PipeGenerator();
        bird = new Bird(this);

        cameraHelper.setTarget(bird);

        birdHitPipe = false;
        gameStart = false;
        gameOver = false;

        if (listener != null)
            listener.gameRestart();
    }

    public void startPipeGenerator() {
        pipeGenerator.setLastPipePositionX(cameraHelper.getPosition().x + 300);
        for (int i = 0; i < 1000; i++) {
            pipeGenerator.generatePipe(pipes);
        }
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (!gameStart) {
                startGame();
            } else if (gameOver) {
                init();
            } else {
                bird.flyUp();
            }
        }
    }

    private void startGame() {
        bird.start();
        startPipeGenerator();
        gameStart = true;
        if (listener != null)
            listener.gameStart();
    }

    public void update(float deltaTime) {
        handleInput();

        bird.update(deltaTime);
        cameraHelper.update();

        background.update(cameraHelper);
        scrollingFloor.update(cameraHelper);

        if (!gameOver) {
            if (scrollingFloor.overlaps(bird)) {
                bird.hitFloor();
            } else if (!birdHitPipe) {
                for (Pipe pipe : pipes) {
                    if (pipe.overlaps(bird)) {
                        bird.hitPipe();
                        break;
                    }
                }
            }
        }

    }

    public CameraHelper getCameraHelper() {
        return cameraHelper;
    }

    public ScrollingFloor getScrollingFloor() {
        return scrollingFloor;
    }

    public Background getBackground() {
        return background;
    }

    public Bird getBird() {
        return bird;
    }

    @Override
    public void hitPipe() {
        birdHitPipe = true;
    }

    @Override
    public void hitFloor() {
        gameOver = true;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Array<Pipe> getPipes() {
        return pipes;
    }

    public void setListener(WorldListener listener) {
        this.listener = listener;
    }
}
