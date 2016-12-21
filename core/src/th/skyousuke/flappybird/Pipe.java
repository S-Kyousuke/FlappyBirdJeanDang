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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Pipe {

    private final static int HALF_WIDTH_DIFFERENCE = 6;
    private final static int HEAD_HEIGHT = 36;

    private PipeBody body;
    private PipeHead head;

    private final boolean invert;

    public Pipe(int height) {
        this(height, false);
    }

    public Pipe(int height, boolean invert) {
        body = new PipeBody(height);
        head = new PipeHead();

        this.invert = invert;
        setPipeBaseLeftPositionX(0);
    }

    public void setPipeBaseLeftPositionX(float x) {

        final int halfScreenHeight = FlappyBirdJeanDang.SCREEN_HEIGHT / 2;
        final float bodyHeight = body.getDimension().y;

        if (!invert) {
            body.setPosition(x, Floor.HEIGHT - halfScreenHeight);
            head.setPosition(x - HALF_WIDTH_DIFFERENCE, Floor.HEIGHT + bodyHeight - halfScreenHeight);
        } else {
            body.setPosition(x, halfScreenHeight - bodyHeight);
            head.setPosition(x - HALF_WIDTH_DIFFERENCE, halfScreenHeight - bodyHeight - HEAD_HEIGHT);
        }
    }

    public void render(SpriteBatch batch) {
        body.render(batch);
        head.render(batch);
    }

    public void debug(ShapeRenderer shapeRenderer) {
        body.debug(shapeRenderer);
        head.debug(shapeRenderer);
    }

    public boolean overlaps(AbstractGameObject object) {
        return body.overlaps(object) || head.overlaps(object);
    }
}
