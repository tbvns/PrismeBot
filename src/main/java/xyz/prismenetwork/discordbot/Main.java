package xyz.prismenetwork.discordbot;

import me.nurio.minecraft.pinger.MinecraftServerPinger;
import me.nurio.minecraft.pinger.beans.MinecraftServerStatus;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import javax.security.auth.login.LoginException;
import java.awt.*;



public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        System.out.println("The bot is starting ...");


//Logging and simple info about the bot
        JDA jda = JDABuilder.createDefault("ODQwOTM4OTgxOTkzNjExMjk0.GvDx1R.esFlzqJaFrxw80SQvDI-ihnDaM2CQtzekWlxBE")
                .setActivity(Activity.playing("on Prisme-Network"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Main())
                .build();


    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
     if (!event.getAuthor().isBot()) {
     //info from whose and where the message came from
             Message message = event.getMessage();
             String text = message.getContentRaw();
             Channel chanel = message.getChannel();

         if (event.getMessage().getContentRaw().contains("pn random")) {
             int min = 0;
             int max = 100;

             //Generate random int value from 50 to 100
             int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
             //message embed for the random command
             MessageEmbed random = new EmbedBuilder()
                     .setTitle("Random number")
                     .addField("Random value between "+min+" to "+max+ ":", String.valueOf(random_int), true)
                     .setColor(Color.GREEN)
                     .build();
             //reply with the embed
             message.reply(" ").addEmbeds(random).queue();

         }
         if (event.getMessage().getContentRaw().contains(" ip")) {
             // embed for the text when someone types ip
             MessageEmbed join = new EmbedBuilder()
                     .setColor(Color.BLUE)
                     .setTitle("How to join the server")
                     .addField("Java:", "Add ____play.prisme-network.xyz____ to your server list.", false)
                     .addField("Bedrock: (Consol)", "Go into your friend tabs and add ____PrismeBot____ to your friend list.", false)
                     .addField("Bedrock: (PC)", "Go into your server tab and add ____play.prisme-network.xyz____ to your server list", false)
                     .build();
             //end of the embed builder
             message.reply(" ").addEmbeds(join).queue();
            }
         if (event.getMessage().getContentRaw().contains("pn info")) {
             MinecraftServerStatus server = MinecraftServerPinger.ping("play.prisme-network.xyz");
             if (server.isOnline()) {
                 int onlineplayer = server.getPlayers().getOnline();
                 String version = server.getVersion().getName();
                 String MOTD = server.getMotd();
                 MessageEmbed serverembed = new EmbedBuilder()
                         .setColor(Color.GREEN)
                         .setTitle("Server info")
                         .addField("Sedrver ip:", "Server ip: ____play.prisme-network.xyz____", false)
                         .addField("Player online:", "There is " + onlineplayer + " online player", false)
                         .addField("Version:", "Server version: " + version, false)
                         .addField("MOTD:", MOTD, true)
                         .build();
             message.reply(" ").addEmbeds(serverembed).queue();

             } else {
             MessageEmbed servererror = new EmbedBuilder()
                     .setColor(Color.RED)
                     .setTitle("Error:")
                     .addField("Server is down:", "The server is offline, try again later.", false)
                     .build();
                     message.reply(" ").addEmbeds(servererror).queue();
             }
           }
         }
       }



}