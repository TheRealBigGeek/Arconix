package com.songoda.arconix.plugin.Commands.Subcommands;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.events.Custom.RegionCreateEvent;
import com.songoda.arconix.api.methods.formatting.TextComponent;
import com.songoda.arconix.api.methods.math.AMath;
import com.songoda.arconix.api.utils.RegionUtils;
import com.songoda.arconix.plugin.Arconix;
import com.songoda.arconix.plugin.Commands.SubCommand;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/12/2017.
 */
public class RegionCMD extends SubCommand {

    private Arconix arconix = Arconix.pl();
    private ArconixAPI api = ArconixAPI.getApi();
    private RegionUtils regionUtils = new RegionUtils();


    public RegionCMD() {
        super("region", "region", "ArconixCMD.command.region", "Stuff for regions", 9);
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(TextComponent.formatText("&e/arconix region selection &7-Enter region selection mode."));
            p.sendMessage(TextComponent.formatText("&e/arconix region list &7-List all the created regions."));
            p.sendMessage(TextComponent.formatText("&e/arconix region create <name> &7-Create a new defined region."));
            p.sendMessage(TextComponent.formatText("&e/arconix region delete <name> &7-Delete an existing region."));
            p.sendMessage(TextComponent.formatText("&e/arconix region edit [Region] [enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
        }

        if (args.length == 2) {

            switch (args[1].toLowerCase()) {

                case "selection":
                    if (!api.inSelectionMode.contains(p)) {
                        api.inSelectionMode.add(p);
                        p.sendMessage(TextComponent.formatText("&eYou are now within region selection mode, select a region by left/right clicking blocks."));
                    } else {
                        api.inSelectionMode.remove(p);
                        p.sendMessage(TextComponent.formatText("&cYou are no longer within region selection mode."));
                    }
                    break;
                case "list":
                    ConfigurationSection section = api.regionFile.getConfig().getConfigurationSection("regions");
                    if (section != null) {
                        p.sendMessage(TextComponent.formatText("&e------------- Created Regions -------------"));
                        int index = 0;
                        int x1, y1, z1, x2, y2, z2;
                        String world;
                        for (String regions : regionUtils.getAllRegions()) {
                            index++;
                            world = api.regionFile.getConfig().getString("regions." + regions + ".location1.world");
                            x1 = api.regionFile.getConfig().getInt("regions." + regions + ".location1.x");
                            y1 = api.regionFile.getConfig().getInt("regions." + regions + ".location1.y");
                            z1 = api.regionFile.getConfig().getInt("regions." + regions + ".location1.z");
                            x2 = api.regionFile.getConfig().getInt("regions." + regions + ".location2.x");
                            y2 = api.regionFile.getConfig().getInt("regions." + regions + ".location2.y");
                            z2 = api.regionFile.getConfig().getInt("regions." + regions + ".location2.z");
                            p.sendMessage(TextComponent.formatText("&6" + index + "&f. &e" + regions + "&f&l| &b" + world + "&d " + x1 + ", " + y1 + ", " + z1 + " &9&l|&e " + x2 + ", " + y2 + ", " + z2));
                        }
                    }
                    break;
                case "create":
                    p.sendMessage(TextComponent.formatText("&e/arconix region create &c<name>"));
                    break;
                case "delete":
                    p.sendMessage(TextComponent.formatText("&e/arconix region delete &c<name>"));
                    break;
                case "edit":
                    p.sendMessage(TextComponent.formatText("&e/arconix region edit &c[Region] [enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
                    break;
            }
        }

        if (args.length == 3) {

            switch (args[1].toLowerCase()) {

                case "create":
                    if (api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cA region by that name already exist!"));
                        return;
                    }

                    if (!api.selectedLocationOne.containsKey(p)) {
                        p.sendMessage(TextComponent.formatText("&cCannot create the region until the first location is defined!"));
                        return;
                    }

                    if (!api.selectedLocationTwo.containsKey(p)) {
                        p.sendMessage(TextComponent.formatText("&cCannot create the region until the second location is defined!"));
                        return;
                    }

                    Location locationOne = api.selectedLocationOne.get(p);
                    Location locationTwo = api.selectedLocationTwo.get(p);

                    RegionCreateEvent regionCreateEvent = new RegionCreateEvent(p, args[2].toLowerCase(), locationOne, locationTwo);
                    arconix.getServer().getPluginManager().callEvent(regionCreateEvent);
                    if (regionCreateEvent.isCancelled()) {
                        return;
                    }
                    regionUtils.createNewRegion(p, args[2], locationOne, locationTwo);
                    break;

                case "delete":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }

                    regionUtils.removeRegion(p, args[2]);
                    break;
                case "edit":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " &c[enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
                    break;
            }
        }

        if (args.length == 4) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            p.sendMessage(TextComponent.formatText("&e/arconix region edit" + args[2] + " enter &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "exit":
                            p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "walk":
                            p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "removeentertitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeentersubtitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeenteractionbar":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.actionbar", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeenterping":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.ping", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeentertext":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.text", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removexittitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeexitsubtitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeexitactionbar":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.actionbar", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeexitping":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.ping", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removeexittext":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.text", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removewalktitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removewalksubtitle":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removewalkactionbar":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.actionbar", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removewalkping":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.ping", null);
                            api.regionFile.saveConfig();
                            break;
                        case "removewalktext":
                            api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.text", null);
                            api.regionFile.saveConfig();
                            break;
                        default:
                            p.sendMessage(TextComponent.formatText("&eremoveentertitle, removeentersubtitle, removeentertext, removeenterping, removeenteractionbar, "
                                    + "removeexittitle, removeexitsubtitle, removeexittext, removeexitping, removeexitactionbar, " +
                                    "removewalktitle, removewalksubtitle, removewalktext, removewalkping, removewalkactionbar"));
                            break;
                    }
            }
        }

        if (args.length == 5) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter ping &c<Msg>"));
                                    p.sendMessage(TextComponent.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit ping &c<Msg>"));
                                    p.sendMessage(TextComponent.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk ping &c<Msg>"));
                                    p.sendMessage(TextComponent.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                    }
            }
        }

        if (args.length >= 6) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " enter text &c<Msg>"));
                                    StringBuilder texttext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.text", texttext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the text for the region " + args[2] + " for onEnter"));
                                    break;
                                case "ping":
                                    StringBuilder pingtext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.ping", pingtext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the ping for the region " + args[2] + " for onEnter"));
                                    break;
                                case "actionbar":
                                    StringBuilder text = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.actionbar", text.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the actionbar for the region " + args[2] + " for onEnter"));
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " exit text &c<Msg>"));
                                    StringBuilder texttext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.text", texttext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the text for the region " + args[2] + " for onExit"));
                                    break;
                                case "ping":
                                    StringBuilder pingtext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.ping", pingtext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the ping for the region " + args[2] + " for onExit"));
                                    break;
                                case "actionbar":
                                    StringBuilder text = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.actionbar", text.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the actionbar for the region " + args[2] + " for onExit"));
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(TextComponent.formatText("&e/arconix region edit " + args[2] + " walk text &c<Msg>"));
                                    StringBuilder texttext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.text", texttext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the text for the region " + args[2] + " for onWalk"));
                                    break;
                                case "ping":
                                    StringBuilder pingtext = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.ping", pingtext.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the ping for the region " + args[2] + " for onWalk"));
                                    break;
                                case "actionbar":
                                    StringBuilder text = new StringBuilder();
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text.append(arg);
                                    }
                                    api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.actionbar", text.toString());
                                    api.regionFile.saveConfig();
                                    p.sendMessage(TextComponent.formatText("&eYou set the actionbar for the region " + args[2] + " for onWalk"));
                                    break;
                            }
                            break;
                    }
            }
        }

        if (args.length >= 9) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!api.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(TextComponent.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the title for the region " + args[2] + " for onEnter"));
                                    }
                                    break;
                                case "subtitle":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the subtitle for the region " + args[2] + " for onEnter"));
                                    }
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the title for the region " + args[2] + " for onExit"));
                                    }
                                    break;
                                case "subtitle":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the subtitle for the region " + args[2] + " for onExit"));
                                    }
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the title for the region " + args[2] + " for onWalk"));
                                    }
                                    break;
                                case "subtitle":
                                    if (AMath.isInt(args[5]) && AMath.isInt(args[6]) && AMath.isInt(args[7])) {
                                        StringBuilder titletext = new StringBuilder();
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext.append(arg);
                                        }
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.fadein", Integer.parseInt(args[5]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.stay", Integer.parseInt(args[6]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.fadeout", Integer.parseInt(args[7]));
                                        api.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.text", titletext.toString());
                                        api.regionFile.saveConfig();
                                        p.sendMessage(TextComponent.formatText("&eYou set the title for the region " + args[2] + " for onWalk"));
                                    }
                                    break;
                            }
                            break;
                    }
            }
        }
    }
}