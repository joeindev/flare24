package dev.flare24.mod.commands.impl;

import dev.flare24.Flare;
import dev.flare24.core.impl.CommandManager;
import dev.flare24.core.impl.ConfigManager;
import dev.flare24.mod.commands.Command;

import java.util.List;

public class ReloadCommand extends Command {

	public ReloadCommand() {
		super("reload", "");
	}

	@Override
	public void runCommand(String[] parameters) {
		CommandManager.sendChatMessage("§fReloading..");
		Flare.CONFIG = new ConfigManager();
		Flare.PREFIX = Flare.CONFIG.getString("prefix", Flare.PREFIX);
		Flare.CONFIG.loadSettings();
		Flare.XRAY.read();
		Flare.TRADE.read();
		Flare.FRIEND.read();
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		return null;
	}
}
