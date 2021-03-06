package com.songoda.arconix.plugin.Commands;

import com.songoda.arconix.api.methods.Formatting;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand implements CommandExecutor {

    private final String command;
    private final String permission;

    private Formatting formatting = new Formatting();

    protected BaseCommand(String command, String permission) {
        this.command = command;
        this.permission = permission;
    }

    public String getName() {
        return command;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(formatting.formatText("&cThis command cannot be ran by non-player users."));
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(formatting.formatText("&cYou do not have permission to use that command!"));
            return true;
        }

        execute((Player) sender, args);
        return true;
    }

    public abstract void execute(Player sender, String[] args);
}