/*
 * Copyright ©2018 HassieSwift621.
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

package uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild;

import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 12:53
 */
public class GuildRankedUser {

    private final long rank;
    private final long score;
    private final IUser user;
    private final long userId;

    public GuildRankedUser(long rank, long score, IUser user, long userId) {
        this.rank = rank;
        this.score = score;
        this.user = user;
        this.userId = userId;
    }

    public long getRank() {
        return rank;
    }

    public long getScore() {
        return score;
    }

    public IUser getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

}