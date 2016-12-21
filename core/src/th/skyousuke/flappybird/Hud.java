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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud implements Disposable, WorldListener {

    private Stage stage;
    private Label startLabel;
    private Label scoreLabel;
    private Label fpsLabel;


    public Hud() {
        stage = new Stage(new FitViewport(FlappyBirdJeanDang.SCREEN_WIDTH, FlappyBirdJeanDang.SCREEN_HEIGHT));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Assets.instance.font;
        labelStyle.fontColor = Color.BLACK;

        startLabel = new Label("คลิกเพื่อเริ่มเล่น", labelStyle);
        startLabel.setPosition(FlappyBirdJeanDang.SCREEN_WIDTH / 2 - startLabel.getWidth() / 2, 450);

        scoreLabel = new Label("คะแนน: 0", labelStyle);
        scoreLabel.setPosition(FlappyBirdJeanDang.SCREEN_WIDTH / 2 - scoreLabel.getWidth() / 2, 550);
        scoreLabel.setVisible(false);

        fpsLabel = new Label("", labelStyle);
        fpsLabel.setPosition(5, 10);

        fpsLabel.addAction(Actions.forever(Actions.delay(1, new Action() {
            @Override
            public boolean act(float delta) {
                fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
                return true;
            }
        })));
        stage.addActor(fpsLabel);
        stage.addActor(startLabel);
        stage.addActor(scoreLabel);
    }

    public void update(float deltaTime) {
        stage.act(deltaTime);
    }

    public void render() {
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void gameStart() {
        startLabel.setVisible(false);
        scoreLabel.setVisible(true);
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void gameRestart() {
        startLabel.setVisible(true);
        scoreLabel.setVisible(false);
    }

    @Override
    public void scoreUpdate(int score) {

    }
}
