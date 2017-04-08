package com.songoda.arconix.Commands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.Subcommands.*;
import com.songoda.arconix.Methods.Formatting;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ArconixCMD extends BaseCommand {

    private final Map<String, SubCommand> children = new HashMap<>();

    private Formatting formatting = new Formatting();

    Arconix plugin = Arconix.pl();

    public ArconixCMD() {

        super("Arconix", "ArconixCMD.command");

        children.put("title", new TitleSubCMD());
        children.put("subtitle", new SubtitleCMD());
        children.put("actionbar", new ActionBarCMD());
        children.put("hologram", new HologramCMD());
        children.put("reload", new ReloadCMD());
        children.put("signeditor", new SignEditorCMD());
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 0) {
            p.sendMessage("");
            p.sendMessage(Arconix.pl().format().formatText("&7[&eArconix&7] " + plugin.getDescription().getVersion() + " Created by &5&l&oBrianna &7& &a&l&oTheCrystalStar"));
            p.sendMessage(formatting.formatText("&a - &7/Arconix reload"));
            p.sendMessage(formatting.formatText("&a - &7/Arconix signeditor"));
            p.sendMessage(formatting.formatText("&a - &7/Arconix title/subtitle <FadeIn> <Stay> <FadeOut> <Text>"));
            p.sendMessage(formatting.formatText("&a - &7/Arconix actionbar <Text>"));
            p.sendMessage(formatting.formatText("&a - &7/Arconix hologram create/delete <Ttitle> [text]"));
            p.sendMessage("");
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
