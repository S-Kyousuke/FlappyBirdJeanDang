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

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {

    private final Vector2 position;
    private AbstractGameObject target;

    public CameraHelper() {
        position = new Vector2();
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public void addPosition(float x, float y) {
        this.position.add(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void applyTo(OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.update();
    }

    public AbstractGameObject getTarget() {
        return target;
    }

    public void setTarget(AbstractGameObject target) {
        this.target = target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(AbstractGameObject target) {
        return this.target.equals(target);
    }

    public void update() {
        if (!hasTarget())
            return;
        position.x = target.getPosition().x + target.getOrigin().x;
        position.y = target.getPosition().y + target.getOrigin().y;
    }

}