package com.songoda.arconix.Commands;

import com.songoda.arconix.Commands.Subcommands.*;
import com.songoda.arconix.Methods.Formatting;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ArconixCMD extends BaseCommand {

    private final Map<String, SubCommand> children = new HashMap<>();

    private Formatting formatting = new Formatting();

    public ArconixCMD() {

        super("Arconix", "ArconixCMD.command");

        children.put("title", new TitleSubCMD());
        children.put("subtitle", new SubtitleCMD());
        children.put("actionbar", new ActionBarCMD());
        children.put("hologram", new HologramCMD());
        children.put("signeditor", new SignEditorCMD());
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 0) {
            p.sendMessage(formatting.formatText("&B&n                                           &r &e&lArconix &B&n                                           "));
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&e/Arconix title/subtitle <FadeIn> <Stay> <FadeOut> <Text>"));
            p.sendMessage(formatting.formatText("&e/Arconix actionbar <Text>"));
            p.sendMessage(formatting.formatText("&e/Arconix hologram hide/show/create <Text>"));
            p.sendMessage(formatting.formatText("&b&n                                                                                                         &r"));
            return;
        }

        SubCommand child = children.get(args[0].toLowerCase());

        if (child != null) {
            child.execute(p, args);
            return;
        }
    }

    public boolean registerChildren(SubCommand command) {

        if (children.get(command.getName()) != null) {
            return false;
        }

        children.put(command.getName(), command);
        return true;
    }
}
