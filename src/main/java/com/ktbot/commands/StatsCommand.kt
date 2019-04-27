package com.ktbot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import net.dv8tion.jda.core.EmbedBuilder
import java.awt.Color
import java.text.DecimalFormat



@CommandInfo(name = ["Stats"], description = "Show statistic from Ayumi bot")
class StatsCommand : Command() {

    init {
        this.name = "stats"
        this.guildOnly = true
        this.help = "Show statistic from Ayumi Bot"
        this.cooldown = 5
    }

    override fun execute(event: CommandEvent) {
        val runtime = Runtime.getRuntime()
        var mb = 1024*1024

        val embed = EmbedBuilder()
                .setTitle("**${event.jda.selfUser.name}'s Statistic**")
                .setThumbnail("https://cdn.discordapp.com/attachments/528569558953492510/570489184981352458/a.png")
                .addField("General Statistic", """
```
Guild           :: In ${event.jda.guilds.size} guilds,
Channel         :: In ${event.jda.textChannels.size + event.jda.voiceChannels.size} channels,
User            :: With ${event.jda.users.size} users
Websocket Ping  :: ${event.jda.ping} ms
```
                """.trimIndent(), false)
                .addField("Memory Statistic", """
```
Used Memory  :: ${runtime.totalMemory() - runtime.totalMemory() / mb} mb
Free Memory  :: ${runtime.freeMemory() / mb} mb
Total Memory :: ${runtime.totalMemory() / mb} mb
Max Memory   :: ${runtime.maxMemory() / mb} mb
```
                """.trimIndent(), false)
                .setColor(Color.BITMASK)
                .setFooter("• Requested By ${event.author.asTag}", "${event.author.effectiveAvatarUrl}")
        event.channel.sendMessage(embed.build()).queue()
    }

}