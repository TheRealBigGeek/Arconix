package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Methods.Formatting;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class ActionBarCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    public ActionBarCMD() {
        super("actionbar", "actionbar", "ArconixCMD.command.actionbar", "Make a actionbar", 2);
    }

    private Arconix arconix = Arconix.getInstance();

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&e/Arconix actionbar <Text>"));
        }

        if (args.length >= 2) {

            String text = "";
            for (int i = 1; i < args.length; i++) {
                String arg = args[i] + " ";
                text = text + arg;
            }
            arconix.packetLibrary.getActionBarManager().sendActionBar(p, formatting.formatText(text));
        }

    }
}
