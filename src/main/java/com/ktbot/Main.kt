package com.ktbot

import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.command.CommandEvent
import com.ktbot.commands.*
import net.dv8tion.jda.core.entities.Game
import java.awt.Color
import java.util.function.Consumer
import lavalink.client.io.jda.JdaLavalink
import net.dv8tion.jda.core.*


object Main {


    @JvmStatic
    fun main(args: Array<String>) {

        val client = CommandClientBuilder()
                .setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26")
                .setOwnerId(Config.OWNER)
                .setPrefix("ay!")
                .setHelpConsumer(Consumer { event: CommandEvent ->
                    val owner = event.jda.getUserById(Config.OWNER).asTag
                    val embed = EmbedBuilder()
                            .setAuthor("${event.jda.selfUser.asTag} Help List", "${event.jda.selfUser.effectiveAvatarUrl}", "${event.jda.selfUser.effectiveAvatarUrl}")
                            .setDescription("Hi **${event.author.asTag}**! My name is **${event.jda.selfUser.name}**. My prefix is `ay!`. Created by **${owner}** using kotlin language and jda library")
                            .addField("<:os:525584598508503040> Core Commands", "`userinfo`, `stats`, `avatar`, `say`, `help`", false)    
                            .setColor(Color.OPAQUE)
                            .setFooter("• Requested By ${event.author.asTag}", "${event.author.effectiveAvatarUrl}")
                    event.channel.sendMessage(embed.build()).queue()
                })
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addCommands(
                        UserInfoCommand(),
                        AvatarCommand(),
                        PingCommand(),
                        SayCommand(),
                        StatsCommand())
                .setGame(Game.playing("WIP."))

        val jda = JDABuilder(AccountType.BOT)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setGame(Game.playing("Loading...."))
                .addEventListener(client.build())
                .setToken(Config.TOKEN)
                .build()
    }
}
