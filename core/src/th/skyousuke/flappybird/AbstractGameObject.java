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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public abstract class AbstractGameObject {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 origin;
    private Vector2 scale;
    private Rectangle bounds;
    private float rotation;

    public AbstractGameObject(float width, float height) {
        position = new Vector2();
        velocity = new Vector2();
        acceleration = new Vector2();

        origin = new Vector2();
        scale = new Vector2(1, 1);
        bounds = new Rectangle();
    }

    public void update(float deltaTime) {
        if (Float.compare(velocity.x, 0) != 0) {
            position.x += velocity.x * deltaTime;
        }
        if (Float.compare(velocity.y, 0) != 0) {
            position.y += velocity.y * deltaTime;
        }
        if (Float.compare(acceleration.x, 0) != 0) {
            velocity.x += acceleration.x * deltaTime;
        }
        if (Float.compare(acceleration.y, 0) != 0) {
            velocity.y += acceleration.y * deltaTime;
        }
    }

    public void render(SpriteBatch batch, TextureRegion region) {
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();

        origin.x = width / 2;
        origin.y = height / 2;

        batch.draw(region,
                position.x, position.y, origin.x, origin.y,
                width, height,
                1.0f, 1.0f, rotation);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }
}
