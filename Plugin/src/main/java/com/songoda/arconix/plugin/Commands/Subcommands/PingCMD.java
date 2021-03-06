package com.songoda.arconix.plugin.Commands.Subcommands;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.plugin.Commands.SubCommand;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class PingCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    public PingCMD() {
        super("ping", "ping", "ArconixCMD.command.ping", "ping", 1);
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&eYour ping is&f: &a" + ArconixAPI.getApi().packetLibrary.getPingManager().getPing(p)));
        }
    }
}
