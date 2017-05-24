package com.songoda.arconix.Commands.Subcommands;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Commands.SubCommand;
import com.songoda.arconix.Methods.Formatting;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashSet;

/**
 * Created by Kiran Hart on 4/7/2017.
 */
public class SignEditorCMD extends SubCommand {

	private Formatting formatting = new Formatting();

	public SignEditorCMD() {
		super("signeditor", "signeditor", "ArconixCMD.command.signeditor", "Re-Open the sign editor", 1);
	}

	private Arconix arconix = Arconix.pl();

	@Override
	public void execute(Player p, String[] args) {

		if (args.length == 1) {

			if (arconix.usingSignEditor.contains(p)) {
				arconix.usingSignEditor.remove(p);
				p.sendMessage(arconix.format().formatText("&cNo longer in sign editing mode."));
			} else {
				arconix.usingSignEditor.add(p);
				p.sendMessage(arconix.format().formatText("&eYou are now in sign editing mode."));
			}
		}
	}
}
