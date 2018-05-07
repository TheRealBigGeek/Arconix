package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Methods.Formatting;
import com.songoda.arconix.method.formatting.TextComponent;
import com.songoda.arconix.method.math.Math;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class HologramCMD extends SubCommand {

    private Formatting formatting = new Formatting();


    Arconix plugin = Arconix.getInstance();

    public HologramCMD() {
        super("hologram", "hologram", "ArconixCMD.command.hologram", "Make a hologram", 4);
    }

    private Arconix arconix = Arconix.getInstance();

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&e/Arconix hologram delete [hologram]"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram create [hologram] [text]"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram move [hologram]"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram addline [hologram] [text]"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram deleteline [hologram] [line]"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram editline [hologram] [line] [text]"));
            p.sendMessage("");
        }

        if (args.length == 2) {

            switch (args[1].toLowerCase()) {
                case "delete":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram delete [hologram]"));
                    break;
                case "create":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram create [hologram] [text]"));
                    break;
                case "move":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram move [hologram]"));
                    break;
                case "addline":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram addline [hologram] [text]"));
                    break;
                case "deleteline":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram deleteline [hologram] [line]"));
                    break;
                case "editline":
                    p.sendMessage(formatting.formatText("&e/Arconix hologram editline [hologram] [line] [text]"));
                    break;
                default:
                    break;
            }
        }

        if (args.length >= 3) {

            switch (args[1].toLowerCase()) {
                case "delete":
                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }
                    arconix.holo.deleteHologram(p, args[2].trim());
                    break;
                case "create":
                    if (plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim())) {
                        p.sendMessage("That hologram already exists.");
                        break;
                    }
                    String text = "";
                    for (int i = 3; i < args.length; i++) {
                        String arg = args[i] + " ";
                        text = text + arg;
                    }
                    arconix.holo.createHologram(p, args[2].trim(), text.trim());
                    break;
                case "info":
                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }
                    List<String> lines = plugin.hologramFile.getConfig().getStringList("Holograms." + args[2].trim() + ".lines");

                    p.sendMessage("");
                    p.sendMessage(TextComponent.formatText("&7Hologram: &e" + args[2].trim() + "&7."));
                    p.sendMessage(TextComponent.formatText("&eLine Number &7/ &eText"));
                    for (int i = 0; i < lines.size(); i++) {
                        p.sendMessage(TextComponent.formatText(" &e" + (i + 1) + " &7/ &e" + lines.get(i)));
                    }
                    p.sendMessage("");

                    break;
                case "move":
                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }

                    arconix.holo.createHologram(p, args[2].trim(), plugin.hologramFile.getConfig().getStringList("Holograms." + args[2].trim() + ".lines"));
                    break;
                case "addline":
                    String txt = "";
                    for (int i = 3; i < args.length; i++) {
                        String arg = args[i] + " ";
                        txt = txt + arg;
                    }

                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }

                    if (args.length < 4) {
                        p.sendMessage("Syntax error...");
                        break;
                    }

                    List<String> lines0 = plugin.hologramFile.getConfig().getStringList("Holograms." + args[2].trim() + ".lines");
                    lines0.add(txt.trim());

                    arconix.holo.editHologram(args[2].trim(), lines0);
                    break;
                case "deleteline":
                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }

                    List<String> lines1 = plugin.hologramFile.getConfig().getStringList("Holograms." + args[2].trim() + ".lines");

                    if (args.length < 4) {
                        p.sendMessage("Syntax error...");
                        break;
                    }

                    int index = NumberUtils.toInt(args[3], Integer.MAX_VALUE) - 1;
                    if (index == Integer.MAX_VALUE) {
                        p.sendMessage("That is not a Number.");
                    } else if (index < 0 || index >= lines1.size()) {
                        p.sendMessage("That number is out of bounds.");
                    }

                    lines1.remove(index);

                    if (lines1.size() == 0)
                        arconix.holo.deleteHologram(p, args[2].trim());
                    else
                        arconix.holo.editHologram(args[2].trim(), lines1);
                    break;
                case "editline":
                    String tx = "";
                    for (int i = 4; i < args.length; i++) {
                        String arg = args[i] + " ";
                        tx = tx + arg;
                    }

                    if (!plugin.hologramFile.getConfig().contains("Holograms." + args[2].trim() + ".lines")) {
                        p.sendMessage("This hologram doesn't exist.");
                        break;
                    }

                    List<String> lines2 = plugin.hologramFile.getConfig().getStringList("Holograms." + args[2].trim() + ".lines");

                    if (args.length < 5) {
                        p.sendMessage("Syntax error...");
                        break;
                    }

                    int index2 = NumberUtils.toInt(args[3], Integer.MAX_VALUE) - 1;
                    if (index2 == Integer.MAX_VALUE) {
                        p.sendMessage("That is not a Number.");
                    } else if (index2 < 0 || index2 >= lines2.size()) {
                        p.sendMessage("That number is out of bounds.");
                    }

                    lines2.set(index2, tx.trim());

                    arconix.holo.editHologram(args[2].trim(), lines2);
                    break;
                default:
                    break;
            }
        }
    }
}
