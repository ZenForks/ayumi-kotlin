package com.ktbot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.doc.standard.CommandInfo
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import java.awt.Color

@CommandInfo(name = ["Userinfo"], description = "see specified info from user")

class UserInfoCommand : Command() {

    init {
        this.name = "userinfo"
        this.cooldown = 5;
        this.help = "see specified info from user"
        this.guildOnly = true
        this.aliases = Array(1){"uinfo"}
    }

    override fun execute(event: CommandEvent) {
        val member: Member
        if (event.message.mentionedMembers.isEmpty()) {
            member = event.member
        } else {
            member = event.message.mentionedMembers[0]
        }

        val Usercreated = member.user.creationTime
        val Userjoined = member.joinDate
        val UserstatusStr = member.onlineStatus.name
        var Userstatus = ""
        val UsertypeBool = member.user.isBot
        var Usertype : String

        if (UserstatusStr === "DO_NOT_DISTURB") {
            Userstatus = "Do Not Disturb"
        } else if (UserstatusStr === "IDLE") {
            Userstatus = "Idling/Idle"
        } else if (UserstatusStr === "OFFLINE") {
            Userstatus = "Offline/Invisible"
        } else if (UserstatusStr === "ONLINE") {
            Userstatus = "Online"
        }
        if (UsertypeBool == true){
            Usertype = "Bot"
        } else {
            Usertype = "Human"
        }

        val embed = EmbedBuilder()
                .setAuthor("${member.user.name}'s Info", "${member.user.effectiveAvatarUrl}", "${member.user.avatarUrl}")
                .addField("General Information", """
```
ID                 :: ${member.user.id} || ${member.user.asTag},
Account created at :: ${Usercreated},
Status             :: ${Userstatus},
Account Type       :: ${Usertype}
```
                """.trimIndent(), false)
                .addField("Guild Information", """
```
Joined this server at :: ${Userjoined}
```
                """.trimIndent(), false)
                .setColor(Color.CYAN)
                .setFooter("• Requested By ${event.author.asTag}", "${event.author.effectiveAvatarUrl}")

        event.channel.sendMessage(embed.build()).queue()
    }
}