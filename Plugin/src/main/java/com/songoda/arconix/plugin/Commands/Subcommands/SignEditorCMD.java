package com.songoda.arconix.plugin.Commands.Subcommands;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.plugin.Commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Kiran Hart on 4/7/2017.
 */
public class SignEditorCMD extends SubCommand {

    private Formatting formatting = new Formatting();

    public SignEditorCMD() {
        super("signeditor", "signeditor", "ArconixCMD.command.signeditor", "Re-Open the sign editor", 1);
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length == 1) {

            if (p.getTargetBlock((Set<Material>) null, 200).getType() != Material.SIGN_POST) {
                p.sendMessage(formatting.formatText("&cPlease look at a sign before using this command!"));
            } else {
                Sign sign = (Sign) p.getTargetBlock((Set<Material>) null, 25).getState();
                ArconixAPI.getApi().packetLibrary.getSignEditorManager().openSignEditor(p, sign);
            }
        }
    }
}
