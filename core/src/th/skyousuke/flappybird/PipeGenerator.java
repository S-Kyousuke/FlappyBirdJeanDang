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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class PipeGenerator {

    private static final int SPACE_HEIGHT = 180;
    private static final int REMAINING_HEIGHT =
            FlappyBirdJeanDang.SCREEN_HEIGHT - SPACE_HEIGHT - PipeHead.HEIGHT * 2 - Floor.HEIGHT;

    private static final int RANDOM_MARGIN = 50;
    private static final int DISTANCE_BETWEEN_PIPE = 250;

    private float lastPipePositionX;

    public void generatePipe(Array<Pipe> pipes) {
        final int pipeHeight = RandomPipeHeight();
        Pipe lowerPipe = new Pipe(pipeHeight);
        Pipe upperPipe = new Pipe(REMAINING_HEIGHT - pipeHeight, true);

        float pipePositionX = lastPipePositionX + DISTANCE_BETWEEN_PIPE;
        lowerPipe.setPipeBaseLeftPositionX(pipePositionX);
        upperPipe.setPipeBaseLeftPositionX(pipePositionX);

        lastPipePositionX = pipePositionX;

        pipes.add(lowerPipe);
        pipes.add(upperPipe);
    }

    public void setLastPipePositionX(float lastPipePositionX) {
        this.lastPipePositionX = lastPipePositionX;
    }

    private int RandomPipeHeight() {
        return MathUtils.random(0 + RANDOM_MARGIN, REMAINING_HEIGHT - RANDOM_MARGIN);
    }

}
