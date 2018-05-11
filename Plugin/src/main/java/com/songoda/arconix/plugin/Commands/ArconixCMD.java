package com.songoda.arconix.plugin.Commands;

import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.plugin.Arconix;
import com.songoda.arconix.plugin.Commands.Subcommands.*;
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
        children.put("reload", new ReloadCMD());
        children.put("signeditor", new SignEditorCMD());
        children.put("region", new RegionCMD());
        children.put("ping", new PingCMD());
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 0) {
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&6&l&n                       &r&e&lArconix&6&l&n                       "));
            p.sendMessage(formatting.formatText("&6Arconix Version: &5" + Arconix.pl().getDescription().getVersion()));
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&f/&eArconix title/subtitle [FadeIn/Stay/FadeOut] <msg>"));
            p.sendMessage(formatting.formatText("&f/&eArconix actionbar <msg>"));
            p.sendMessage(formatting.formatText("&f/&eArconix hologram &f- &bHas Subcommands"));
            p.sendMessage(formatting.formatText("&f/&eArconix reload"));
            p.sendMessage(formatting.formatText("&f/&eArconix signeditor"));
            p.sendMessage(formatting.formatText("&f/&eArconix region &f- &bHas Subcommands"));
            p.sendMessage(formatting.formatText("&f/&eArconix ping"));
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&c&lDocumentation&f: &Ahttp://wiki.songoda.com/arconix"));
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&b&lAbout&f: &7Created by &d&lBrianna &f&l& &a&lTheCrystalStar"));
            p.sendMessage(formatting.formatText("&7Is an API designed to provide all the functions that"));
            p.sendMessage(formatting.formatText("&7usually will require you to download multiple APIs."));
            p.sendMessage("");
            p.sendMessage(formatting.formatText("&d&lBrianna&F: &ehttps://www.spigotmc.org/members/216944/"));
            p.sendMessage(formatting.formatText("&a&lTheCrystalStar&F: &ehttps://www.spigotmc.org/members/99189/"));
            p.sendMessage(formatting.formatText("&6&l&n                                                       "));
            return;
        }

        SubCommand child = children.get(args[0].toLowerCase());

        if (child != null) {
            child.execute(p, args);
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
