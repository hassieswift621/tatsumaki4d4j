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

package uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.client;

import okhttp3.OkHttpClient;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import uk.co.hassieswift621.libraries.asyncthreader.AsyncThreader;
import uk.co.hassieswift621.libraries.asyncthreader.Request;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildLeaderboard;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserPoints;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserScore;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserStats;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.user.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.utils.GuildUpdateAction;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:50.
 */
public class TatsumakiClient {

    private final AsyncThreader asyncThreader;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Requests requests;

    public static class Builder {

        private int threadPoolSize = Runtime.getRuntime().availableProcessors() + 1;
        private String token;

        public Builder setThreadPoolSize(int threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public TatsumakiClient build() {
            return new TatsumakiClient(token, new AsyncThreader.Builder()
                    .setThreadPoolSize(threadPoolSize)
                    .build());
        }
    }

    public TatsumakiClient(String token) {
        this.asyncThreader = new AsyncThreader();
        this.requests = new Requests(httpClient, token);
    }

    private TatsumakiClient(String token, AsyncThreader asyncThreader) {
        this.asyncThreader = asyncThreader;
        this.requests = new Requests(httpClient, token);
    }

    public void getGuildLeaderboard(IDiscordClient client, long guildId, Response<GuildLeaderboard> response, Error error) {
        Request<GuildLeaderboard> request = new Request<>(
                () -> requests.getGuildLeaderboard(client, guildId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getGuildLeaderboard(IDiscordClient client, String guildId, Response<GuildLeaderboard> response, Error error) {
        Request<GuildLeaderboard> request = new Request<>(
                () -> requests.getGuildLeaderboard(client, guildId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getGuildLeaderboard(IDiscordClient client, IGuild guild, Response<GuildLeaderboard> response, Error error) {
        Request<GuildLeaderboard> request = new Request<>(
                () -> requests.getGuildLeaderboard(client, guild),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getGuildUserStats(IDiscordClient client, long guildId, long userId, Response<GuildUserStats> response, Error error) {
        Request<GuildUserStats> request = new Request<>(
                () -> requests.getGuildUserStats(client, guildId, userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getGuildUserStats(IDiscordClient client, String guildId, String userId, Response<GuildUserStats> response, Error error) {
        Request<GuildUserStats> request = new Request<>(
                () -> requests.getGuildUserStats(client, guildId, userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getGuildUserStats(IDiscordClient client, IGuild guild, IUser user, Response<GuildUserStats> response, Error error) {
        Request<GuildUserStats> request = new Request<>(
                () -> requests.getGuildUserStats(client, guild, user),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getPing(Response<Ping> response, Error error) {
        Request<Ping> request = new Request<>(
                requests::getPing,
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getUser(IDiscordClient client, long userId, Response<TatsumakiUser> response, Error error) {
        Request<TatsumakiUser> request = new Request<>(
                () -> requests.getUser(client, userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getUser(IDiscordClient client, String userId, Response<TatsumakiUser> response, Error error) {
        Request<TatsumakiUser> request = new Request<>(
                () -> requests.getUser(client, userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void getUser(IDiscordClient client, IUser user, Response<TatsumakiUser> response, Error error) {
        Request<TatsumakiUser> request = new Request<>(
                () -> requests.getUser(client, user),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserPoints(IDiscordClient client, long guildId, long userId, GuildUpdateAction action,
                                      int amount, Response<GuildUserPoints> response, Error error) {
        Request<GuildUserPoints> request = new Request<>(
                () -> requests.updateGuildUserPoints(client, guildId, userId, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserPoints(IDiscordClient client, String guildId, String userId, GuildUpdateAction action,
                                      int amount, Response<GuildUserPoints> response, Error error) {
        Request<GuildUserPoints> request = new Request<>(
                () -> requests.updateGuildUserPoints(client, guildId, userId, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserPoints(IDiscordClient client, IGuild guild, IUser user, GuildUpdateAction action,
                                      int amount, Response<GuildUserPoints> response, Error error) {
        Request<GuildUserPoints> request = new Request<>(
                () -> requests.updateGuildUserPoints(client, guild, user, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserScore(IDiscordClient client, long guildId, long userId, GuildUpdateAction action,
                                     int amount, Response<GuildUserScore> response, Error error) {
        Request<GuildUserScore> request = new Request<>(
                () -> requests.updateGuildUserScore(client, guildId, userId, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserScore(IDiscordClient client, String guildId, String userId, GuildUpdateAction action,
                                     int amount, Response<GuildUserScore> response, Error error) {
        Request<GuildUserScore> request = new Request<>(
                () -> requests.updateGuildUserScore(client, guildId, userId, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    public void updateGuildUserScore(IDiscordClient client, IGuild guild, IUser user, GuildUpdateAction action,
                                     int amount, Response<GuildUserScore> response, Error error) {
        Request<GuildUserScore> request = new Request<>(
                () -> requests.updateGuildUserScore(client, guild, user, action, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Shuts down Async Threader and OkHttp to shutdown executors which keep program alive.
     */
    public void shutdown() {
        asyncThreader.shutdown();
        httpClient.dispatcher().executorService().shutdown();
    }

}