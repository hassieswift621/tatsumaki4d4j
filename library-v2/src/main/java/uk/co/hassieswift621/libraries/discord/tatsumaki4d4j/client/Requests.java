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

import okhttp3.*;
import okhttp3.Response;
import org.json.JSONObject;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.exceptions.TatsumakiException;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildLeaderboard;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserPoints;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserScore;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.guild.GuildUserStats;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.handle.user.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.utils.GuildUpdateAction;
import uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.utils.Parser;
import uk.co.hassieswift621.libraries.jsonio.JsonIO;
import uk.co.hassieswift621.libraries.jsonio.exceptions.JsonIOException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 11:23
 */
public class Requests {

    private final OkHttpClient httpClient;
    private final String token;

    public Requests(OkHttpClient httpClient, String token) {
        this.httpClient = httpClient;
        this.token = token;
    }

    public GuildLeaderboard getGuildLeaderboard(IDiscordClient client, long guildId) throws TatsumakiException {
        return Parser.parseGuildLeaderboard(makeGetRequest(Endpoints.getGuildLeaderboard(guildId)), client, guildId);
    }

    public GuildLeaderboard getGuildLeaderboard(IDiscordClient client, String guildId) throws TatsumakiException {
        return getGuildLeaderboard(client, Parser.parseGuildId(guildId));
    }

    public GuildLeaderboard getGuildLeaderboard(IDiscordClient client, IGuild guild) throws TatsumakiException {
        return getGuildLeaderboard(client, guild.getLongID());
    }

    public GuildUserStats getGuildUserStats(IDiscordClient client, long guildId, long userId) throws TatsumakiException {
        return Parser.parseGuildUserStats(makeGetRequest(Endpoints.getGuildUserStats(guildId, userId)), client);
    }

    public GuildUserStats getGuildUserStats(IDiscordClient client, String guildId, String userId) throws TatsumakiException {
        return getGuildUserStats(client, Parser.parseGuildId(guildId), Parser.parseUserId(userId));
    }

    public GuildUserStats getGuildUserStats(IDiscordClient client, IGuild guild, IUser user) throws TatsumakiException {
        return getGuildUserStats(client, guild.getLongID(), user.getLongID());
    }

    public Ping getPing() throws TatsumakiException {
        return Parser.parsePing(makeGetRequest(Endpoints.getPing()));
    }

    public TatsumakiUser getUser(IDiscordClient client, long userId) throws TatsumakiException {
        return Parser.parseUser(makeGetRequest(Endpoints.getUser(userId)), client, userId);
    }

    public TatsumakiUser getUser(IDiscordClient client, String userId) throws TatsumakiException {
        return getUser(client, Parser.parseUserId(userId));
    }

    public TatsumakiUser getUser(IDiscordClient client, IUser user) throws TatsumakiException {
        return getUser(client, user.getLongID());
    }

    public GuildUserPoints updateGuildUserPoints(IDiscordClient client, long guildId, long userId, GuildUpdateAction action,
                                                 int amount) throws TatsumakiException {
        return Parser.parseGuildUserPoints(makePutRequest(
                Endpoints.putGuildUserPoints(guildId, userId),
                Parser.createGuildUserPointsRequest(action, amount)), client, guildId, userId);
    }

    public GuildUserPoints updateGuildUserPoints(IDiscordClient client, String guildId, String userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return updateGuildUserPoints(client, Parser.parseGuildId(guildId), Parser.parseUserId(userId), action, amount);
    }

    public GuildUserPoints updateGuildUserPoints(IDiscordClient client, IGuild guild, IUser user, GuildUpdateAction action,
                                                 int amount) throws TatsumakiException {
        return updateGuildUserPoints(client, guild.getLongID(), user.getLongID(), action, amount);
    }

    public GuildUserScore updateGuildUserScore(IDiscordClient client, long guildId, long userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return Parser.parseGuildUserScore(makePutRequest(
                Endpoints.putGuildUserScore(guildId, userId),
                Parser.createGuildUserScoreRequest(action, amount)), client, guildId, userId);
    }

    public GuildUserScore updateGuildUserScore(IDiscordClient client, String guildId, String userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return updateGuildUserScore(client, Parser.parseGuildId(guildId), Parser.parseUserId(userId), action, amount);
    }

    public GuildUserScore updateGuildUserScore(IDiscordClient client, IGuild guild, IUser user, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return updateGuildUserScore(client, guild.getLongID(), user.getLongID(), action, amount);
    }

    private InputStream makeGetRequest(String url) throws TatsumakiException {

        Request request = new Request.Builder()
                .get()
                .header("Authorization", token)
                .url(url)
                .build();

        return getResponse(request);
    }

    private InputStream makePutRequest(String url, String json) throws TatsumakiException {

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .put(requestBody)
                .header("Authorization", token)
                .url(url)
                .build();

        return getResponse(request);
    }

    private InputStream getResponse(Request request) throws TatsumakiException {

        try {

            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                return response.body().byteStream();
            }

            // Parse error.
            if (response.body() != null) {

                JSONObject error = JsonIO.toJson(response.body().byteStream());
                throw new TatsumakiException(error.getString("message"));
            }

            throw new TatsumakiException("Failed to get response");

        } catch (IOException | JsonIOException e) {
            throw new TatsumakiException("Failed to get response", e);
        }
    }

}