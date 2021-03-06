package com.songoda.arconix.plugin.Commands.Subcommands;

import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.plugin.Arconix;
import com.songoda.arconix.plugin.Commands.SubCommand;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class ActionBarCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    public ActionBarCMD() {
        super("actionbar", "actionbar", "ArconixCMD.command.actionbar", "Make a actionbar", 2);
    }

    private Arconix arconix = Arconix.pl();

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&e/Arconix actionbar <Text>"));
        }

        if (args.length >= 2) {

            StringBuilder text = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                String arg = args[i] + " ";
                text.append(arg);
            }
            arconix.getApi().packetLibrary.getActionBarManager().sendActionBar(p, formatting.formatText(text.toString()));
        }

    }
}
