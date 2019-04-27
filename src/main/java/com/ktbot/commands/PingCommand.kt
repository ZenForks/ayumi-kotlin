package com.ktbot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message
import java.time.temporal.ChronoUnit

@CommandInfo(name = ["Ping"], description = "Checks the bot's latency")
class PingCommand : Command(){

    init {
        this.name = "ping"
        this.cooldown = 5;
        this.help = "Checks the bot latency"
        this.guildOnly = true
    }

    override fun execute(event: CommandEvent) {
       event.channel.sendMessage("Pinging....").queue({ msg: Message ->
           val latency = event.message.creationTime.until(msg.creationTime, ChronoUnit.MILLIS)
           val embed = EmbedBuilder()
                   .setAuthor("\uD83C\uDFD3 Pong!", "${event.author.effectiveAvatarUrl}", "${event.author.effectiveAvatarUrl}")
                   .addField("Latency", "__**${latency}ms**__", false)
                   .addField("Websocket", "__**${event.jda.ping}ms**__", false)
           msg.delete()
           event.channel.sendMessage(embed.build()).queue()
       })
    }
}
