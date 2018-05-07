package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Events.Custom.RegionCreateEvent;
import com.songoda.arconix.Methods.Formatting;
import com.songoda.arconix.Methods.Maths;
import com.songoda.arconix.Utils.RegionUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/12/2017.
 */
public class RegionCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    private Arconix arconix = Arconix.getInstance();
    private RegionUtils regionUtils = new RegionUtils();


    public RegionCMD() {
        super("region", "region", "ArconixCMD.command.region", "Stuff for regions", 9);
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {
            p.sendMessage(formatting.formatText("&e/arconix region selection &7-Enter region selection mode."));
            p.sendMessage(formatting.formatText("&e/arconix region list &7-List all the created regions."));
            p.sendMessage(formatting.formatText("&e/arconix region create <name> &7-Create a new defined region."));
            p.sendMessage(formatting.formatText("&e/arconix region delete <name> &7-Delete an existing region."));
            p.sendMessage(formatting.formatText("&e/arconix region edit [Region] [enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
        }

        if (args.length == 2) {

            switch (args[1].toLowerCase()) {

                case "selection":
                    if (!arconix.inSelectionMode.contains(p)) {
                        arconix.inSelectionMode.add(p);
                        p.sendMessage(formatting.formatText("&eYou are now within region selection mode, select a region by left/right clicking blocks."));
                    } else {
                        arconix.inSelectionMode.remove(p);
                        p.sendMessage(formatting.formatText("&cYou are no longer within region selection mode."));
                    }
                    break;
                case "list":
                    ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions");
                    if (section != null) {
                        p.sendMessage(formatting.formatText("&e------------- Created Regions -------------"));
                        int index = 0;
                        int x1, y1, z1, x2, y2, z2;
                        String world;
                        for (String regions : regionUtils.getAllRegions()) {
                            index++;
                            world = arconix.regionFile.getConfig().getString("regions." + regions + ".location1.world");
                            x1 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location1.x");
                            y1 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location1.y");
                            z1 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location1.z");
                            x2 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location2.x");
                            y2 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location2.y");
                            z2 = arconix.regionFile.getConfig().getInt("regions." + regions + ".location2.z");
                            p.sendMessage(formatting.formatText("&6" + index + "&f. &e" + regions + "&f&l| &b" + world + "&d " + x1 + ", " + y1 + ", " + z1 + " &9&l|&e " + x2 + ", " + y2 + ", " + z2));
                        }
                    }
                    break;
                case "create":
                    p.sendMessage(formatting.formatText("&e/arconix region create &c<name>"));
                    break;
                case "delete":
                    p.sendMessage(formatting.formatText("&e/arconix region delete &c<name>"));
                    break;
                case "edit":
                    p.sendMessage(formatting.formatText("&e/arconix region edit &c[Region] [enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
                    break;
            }
        }

        if (args.length == 3) {

            switch (args[1].toLowerCase()) {

                case "create":
                    if (arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cA region by that name already exist!"));
                        return;
                    }

                    if (!arconix.selectedLocationOne.containsKey(p)) {
                        p.sendMessage(formatting.formatText("&cCannot create the region until the first location is defined!"));
                        return;
                    }

                    if (!arconix.selectedLocationTwo.containsKey(p)) {
                        p.sendMessage(formatting.formatText("&cCannot create the region until the second location is defined!"));
                        return;
                    }

                    Location locationOne = arconix.selectedLocationOne.get(p);
                    Location locationTwo = arconix.selectedLocationTwo.get(p);

                    RegionCreateEvent regionCreateEvent = new RegionCreateEvent(p, args[2].toLowerCase(), locationOne, locationTwo);
                    arconix.getServer().getPluginManager().callEvent(regionCreateEvent);
                    if (regionCreateEvent.isCancelled()) {
                        return;
                    }
                    regionUtils.createNewRegion(p, args[2], locationOne, locationTwo);
                    break;

                case "delete":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }

                    regionUtils.removeRegion(p, args[2]);
                    break;
                case "edit":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " &c[enter|exit|walk] [title|subtitle|text|ping|actionbar]"));
                    break;
            }
        }

        if (args.length == 4) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            p.sendMessage(formatting.formatText("&e/arconix region edit" + args[2] + " enter &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "exit":
                            p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "walk":
                            p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk &c[title|subtitle|text|ping|actionbar]"));
                            break;
                        case "removeentertitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeentersubtitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeenteractionbar":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.actionbar", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeenterping":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.ping", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeentertext":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.text", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removexittitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeexitsubtitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeexitactionbar":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.actionbar", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeexitping":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.ping", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removeexittext":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.text", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removewalktitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removewalksubtitle":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removewalkactionbar":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.actionbar", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removewalkping":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.ping", null);
                            arconix.regionFile.saveConfig();
                            break;
                        case "removewalktext":
                            arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.text", null);
                            arconix.regionFile.saveConfig();
                            break;
                        default:
                        	p.sendMessage(arconix.format().formatText("&eremoveentertitle, removeentersubtitle, removeentertext, removeenterping, removeenteractionbar, "
                        			+ "removeexittitle, removeexitsubtitle, removeexittext, removeexitping, removeexitactionbar, " +
                        			"removewalktitle, removewalksubtitle, removewalktext, removewalkping, removewalkactionbar"));
                        	break;
                    }
            }
        }

        if (args.length == 5) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter ping &c<Msg>"));
                                    p.sendMessage(formatting.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit ping &c<Msg>"));
                                    p.sendMessage(formatting.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk title &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "subtitle":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk subtitle &c[FadeIn] [Stay] [FadeOut] <Msg>"));
                                    break;
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk text &c<Msg>"));
                                    break;
                                case "ping":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk ping &c<Msg>"));
                                    p.sendMessage(formatting.formatText("&7{ping} can be used to get the ping of a user."));
                                    break;
                                case "actionbar":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk actionbar &c<Msg>"));
                                    break;
                            }
                            break;
                    }
            }
        }

        if (args.length >= 6) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " enter text &c<Msg>"));
                                    String texttext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext = texttext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.text", texttext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the text for the region " + args[2] + " for onEnter"));
                                    break;
                                case "ping":
                                    String pingtext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext = pingtext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.ping", pingtext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the ping for the region " + args[2] + " for onEnter"));
                                    break;
                                case "actionbar":
                                    String text = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text = text + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.actionbar", text);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the actionbar for the region " + args[2] + " for onEnter"));
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " exit text &c<Msg>"));
                                    String texttext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext = texttext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.text", texttext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the text for the region " + args[2] + " for onExit"));
                                    break;
                                case "ping":
                                    String pingtext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext = pingtext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.ping", pingtext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the ping for the region " + args[2] + " for onExit"));
                                    break;
                                case "actionbar":
                                    String text = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text = text + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.actionbar", text);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the actionbar for the region " + args[2] + " for onExit"));
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "text":
                                    p.sendMessage(formatting.formatText("&e/arconix region edit " + args[2] + " walk text &c<Msg>"));
                                    String texttext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        texttext = texttext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.text", texttext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the text for the region " + args[2] + " for onWalk"));
                                    break;
                                case "ping":
                                    String pingtext = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        pingtext = pingtext + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.ping", pingtext);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the ping for the region " + args[2] + " for onWalk"));
                                    break;
                                case "actionbar":
                                    String text = "";
                                    for (int i = 5; i < args.length; i++) {
                                        String arg = args[i] + " ";
                                        text = text + arg;
                                    }
                                    arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.actionbar", text);
                                    arconix.regionFile.saveConfig();
                                    p.sendMessage(formatting.formatText("&eYou set the actionbar for the region " + args[2] + " for onWalk"));
                                    break;
                            }
                            break;
                    }
            }
        }

        if (args.length >= 9) {

            switch (args[1].toLowerCase()) {
                case "edit":
                    if (!arconix.regionFile.getConfig().contains("regions." + args[2].toLowerCase())) {
                        p.sendMessage(formatting.formatText("&cNo region(s) by that name exist!"));
                        return;
                    }
                    switch (args[3].toLowerCase()) {
                        case "enter":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.title.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the title for the region " + args[2] + " for onEnter"));
                                    }
                                    break;
                                case "subtitle":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onEnter.subtitle.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the subtitle for the region " + args[2] + " for onEnter"));
                                    }
                                    break;
                            }
                            break;
                        case "exit":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.title.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the title for the region " + args[2] + " for onExit"));
                                    }
                                    break;
                                case "subtitle":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onExit.subtitle.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the subtitle for the region " + args[2] + " for onExit"));
                                    }
                                    break;
                            }
                            break;
                        case "walk":
                            switch (args[4].toLowerCase()) {
                                case "title":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.title.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the title for the region " + args[2] + " for onWalk"));
                                    }
                                    break;
                                case "subtitle":
                                    if (Maths.isInt(args[5]) && Maths.isInt(args[6]) && Maths.isInt(args[7])) {
                                        String titletext = "";
                                        for (int i = 8; i < args.length; i++) {
                                            String arg = args[i] + " ";
                                            titletext = titletext + arg;
                                        }
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.fadein", Integer.parseInt(args[5]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.stay", Integer.parseInt(args[6]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.fadeout", Integer.parseInt(args[7]));
                                        arconix.regionFile.getConfig().set("regions." + args[2].toLowerCase() + ".activations.onWalk.subtitle.text", titletext);
                                        arconix.regionFile.saveConfig();
                                        p.sendMessage(formatting.formatText("&eYou set the title for the region " + args[2] + " for onWalk"));
                                    }
                                    break;
                            }
                            break;
                    }
            }
        }
    }
}
