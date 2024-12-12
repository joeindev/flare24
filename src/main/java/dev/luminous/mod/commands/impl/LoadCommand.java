package dev.flare24.mod.commands.impl;

import dev.flare24.Flare;
import dev.flare24.core.Manager;
import dev.flare24.core.impl.CommandManager;
import dev.flare24.core.impl.ConfigManager;
import dev.flare24.mod.commands.Command;

import java.util.List;

public class LoadCommand extends Command {

	public LoadCommand() {
		super("load", "[config]");
	}

	@Override
	public void runCommand(String[] parameters) {
		if (parameters.length == 0) {
			sendUsage();
			return;
		}
		CommandManager.sendChatMessage("§fLoading..");
		ConfigManager.options = Manager.getFile(parameters[0] + ".cfg");
		Flare.CONFIG = new ConfigManager();
		Flare.PREFIX = Flare.CONFIG.getString("prefix", Flare.PREFIX);
		Flare.CONFIG.loadSettings();
        ConfigManager.options = Manager.getFile("options.txt");
		Flare.save();
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		return null;
	}
}
