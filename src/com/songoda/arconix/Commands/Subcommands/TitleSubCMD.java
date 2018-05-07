package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Methods.Formatting;
import com.songoda.arconix.Methods.Maths;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class TitleSubCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    public TitleSubCMD() {
        super("title", "title", "ArconixCMD.command.title", "Make a title", 5);
    }

    private Arconix arconix = Arconix.getInstance();

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&e/Arconix title <FadeIn> <Stay> <FadeOut> <Text>"));
        }

        if (args.length == 2) {
            if (Maths.isInt(args[1])) {
                p.sendMessage(formatting.formatText("&e/Arconix title " + args[1] + " &c<Stay> <FadeOut> <Text>"));
            }
        }

        if (args.length == 3) {
            if (Maths.isInt(args[1]) && Maths.isInt(args[2])) {
                p.sendMessage(formatting.formatText("&e/Arconix title " + args[1] + " " + args[2] + " &c<FadeOut> <Text>"));
            }
        }

        if (args.length == 4) {
            if (Maths.isInt(args[1]) && Maths.isInt(args[2]) && Maths.isInt(args[3])) {
                p.sendMessage(formatting.formatText("&e/Arconix title " + args[1] + " " + args[2] + " " + args[3] + " &c<Text>"));
            }
        }

        if (args.length >= 5) {
            if (Maths.isInt(args[1]) && Maths.isInt(args[2]) && Maths.isInt(args[3])) {
                String text = "";
                for (int i = 4; i < args.length; i++) {
                    String arg = args[i] + " ";
                    text = text + arg;
                }
                arconix.packetLibrary.getTitleManager().sendTitle(p, text, 20, 40, 20);
            }
        }
    }
}
