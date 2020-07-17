package net.comorevi.np.moneys.jobs.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.comorevi.np.moneys.jobs.form.FormHandler;

public class JobsCommand extends Command {

    public JobsCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandSender.isPlayer()) {
            commandSender.sendMessage("[MoneySJobs] ゲーム内からのみ実行できます。");
            return true;
        } else if (!commandSender.hasPermission("moneys.jobs.jobs")) {
            commandSender.sendMessage("[MoneySJobs] コマンド実行に必要な権限がありません。");
            return true;
        }

        FormHandler.getInstance().sendJobsHome((Player) commandSender, FormHandler.PlayerClient.COMMAND);
        return true;
    }
}
