package com.songoda.arconix.Commands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.Subcommands.*;
import com.songoda.arconix.method.formatting.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ArconixCMD extends BaseCommand {

    private final Map<String, SubCommand> children = new HashMap<>();


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
            p.sendMessage(TextComponent.formatText("&eArconix &7" + Arconix.getInstance().getDescription().getVersion() + " Created by &5&l&oBrianna"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7title/subtitle [FadeIn/Stay/FadeOut] <msg>"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7actionbar <msg>"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7hologram &f- &bHas Subcommands"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7reload"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7signeditor"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7region &f- &bHas Subcommands"));
            p.sendMessage(TextComponent.formatText(" &8- " + "&eArconix &7ping"));
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
