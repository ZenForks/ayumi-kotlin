package com.ktbot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import java.awt.Color

@CommandInfo(name = ["Avatar"], description = "Show someone avatar")
class AvatarCommand : Command() {

    init {
        this.guildOnly = true
        this.help = "Show someone avatar"
        this.name = "avatar"
        this.aliases = Array(1){"ava"}
        this.cooldown = 5
    }

    override fun execute(event: CommandEvent) {
        val member: Member
        if (event.message.mentionedMembers.isEmpty()) {
            member = event.member
        } else {
            member = event.message.mentionedMembers[0]
        }

        val embed = EmbedBuilder()
                .setAuthor("${member.user.name}'s Avatar", "${member.user.avatarUrl}", "${member.user.effectiveAvatarUrl}")
                .setDescription("**[Picture Link](${member.user.avatarUrl}?size=2048)**")
                .setImage("${member.user.avatarUrl}?size=2048")
                .setColor(Color.OPAQUE)
                .setFooter("• Requested By ${event.author.asTag}", "${event.author.effectiveAvatarUrl}")
       event.channel.sendMessage(embed.build()).queue()
    }
}