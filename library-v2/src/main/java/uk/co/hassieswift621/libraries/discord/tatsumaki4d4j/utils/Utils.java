package uk.co.hassieswift621.libraries.discord.tatsumaki4d4j.utils;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Hassie on Friday, 27 July, 2018 - 19:09
 */
public class Utils {

    public static IGuild getGuildById(IDiscordClient client, long guildId) {
        return client.getGuildByID(guildId);
    }

    public static IUser getUserById(IDiscordClient client, long userId) {
        return client.fetchUser(userId);
    }

}
