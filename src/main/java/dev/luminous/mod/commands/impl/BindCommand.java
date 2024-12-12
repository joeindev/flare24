package dev.flare24.mod.commands.impl;

import dev.flare24.mod.commands.Command;
import dev.flare24.Flare;
import dev.flare24.core.impl.CommandManager;
import dev.flare24.mod.modules.Module;
import dev.flare24.core.impl.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class BindCommand extends Command {

	public BindCommand() {
		super("bind", "[module] [key]");
	}

	@Override
	public void runCommand(String[] parameters) {
		if (parameters.length == 0) {
			sendUsage();
			return;
		}
		String moduleName = parameters[0];
		Module module = Flare.MODULE.getModuleByName(moduleName);
		if (module == null) {
			CommandManager.sendChatMessage("§4Unknown module!");
			return;
		}
		if (parameters.length == 1) {
			CommandManager.sendChatMessage("§fPlease specify a §bkey§f.");
			return;
		}
		String rkey = parameters[1];
		if (rkey == null) {
			CommandManager.sendChatMessage("§4Unknown Error");
			return;
		}
		if (module.setBind(rkey.toUpperCase())) {
			CommandManager.sendChatMessage("§fBind for §r" + module.getName() + "§f set to §r" + rkey.toUpperCase());
		}
	}

	@Override
	public String[] getAutocorrect(int count, List<String> seperated) {
		if (count == 1) {
			String input = seperated.get(seperated.size() - 1).toLowerCase();
			ModuleManager cm = Flare.MODULE;
			List<String> correct = new ArrayList<>();
			for (Module x : cm.modules) {
				if (input.equalsIgnoreCase(Flare.PREFIX + "bind") || x.getName().toLowerCase().startsWith(input)) {
					correct.add(x.getName());
				}
			}
			int numCmds = correct.size();
			String[] commands = new String[numCmds];

			int i = 0;
			for (String x : correct) {
				commands[i++] = x;
			}

			return commands;
		}
		return null;
	}
}
