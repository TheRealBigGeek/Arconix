package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Methods.Formatting;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class PingCMD extends SubCommand {

    private Formatting formatting = new Formatting();
    private Arconix arconix = Arconix.getInstance();

    public PingCMD() {
        super("ping", "ping", "ArconixCMD.command.ping", "ping", 1);
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&eYour ping is&f: &a" + arconix.packetLibrary.getPingManager().getPing(p)));
        }
    }
}
