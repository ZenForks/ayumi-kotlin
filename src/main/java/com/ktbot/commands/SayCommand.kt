package com.ktbot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import net.dv8tion.jda.core.Permission

@CommandInfo(name = ["Say"], description = "Makes bot say something.")
class SayCommand : Command() {

    init {
        this.name = "say"
        this.help = "Makes bot say something"
        this.cooldown = 5
        this.guildOnly = true
    }

    override fun execute(event: CommandEvent) {
        var allArgs = event.message.contentDisplay
        if (allArgs.contains(" ")) {
            allArgs = allArgs.substring(allArgs.indexOf(' ')).trim().replace("@everyone", "`@everyone`").replace("@here", "`@here`")
        }

        if (allArgs.isEmpty()) {
            return event.message.channel.sendMessage("**${event.author.name}**, please specify a text!").queue()
        } else {
            if (event.guild.selfMember.hasPermission(Permission.MESSAGE_MANAGE)) {
                event.message.delete().queue()
                event.message.channel.sendMessage(allArgs).queue()
            } else {
                event.message.channel.sendMessage(allArgs).queue()
            }
        }
    }
}