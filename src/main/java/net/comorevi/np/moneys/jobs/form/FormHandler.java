package net.comorevi.np.moneys.jobs.form;

import cn.nukkit.Player;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;
import net.comorevi.cphone.presenter.SharingData;
import net.comorevi.np.moneys.MoneySAPI;
import net.comorevi.np.moneys.jobs.data.AvailableJobs;
import net.comorevi.np.moneys.jobs.manager.JobDataHandler;
import net.comorevi.np.moneys.jobs.manager.LevelCalculator;
import ru.nukkitx.forms.elements.ImageType;
import ru.nukkitx.forms.elements.ModalForm;
import ru.nukkitx.forms.elements.SimpleForm;

import java.util.LinkedHashMap;

public class FormHandler {
    private static final FormHandler instance = new FormHandler();
    private LinkedHashMap<String, EnumPlayerClient> playerClient = new LinkedHashMap();

    public void sendJobsHome(Player player, EnumPlayerClient client) {
        playerClient.put(player.getName(), client);
        sendJobsHome(player, "操作を選択してください。");
    }

    private void sendJobsHome(Player player) {
        sendJobsHome(player, "操作を選択してください。");
    }

    private void sendJobsHome(Player player, String homeMessage) {
        new SimpleForm()
                .setTitle("ジョブシステム")
                .setContent(homeMessage)
                .addButton("ジョブチェンジ", ImageType.PATH, "textures/ui/FriendsIcon")
                .addButton("情報を見る", ImageType.PATH, "textures/ui/creative_icon")
                .send(player, (target, form, data) -> {
                    if (data == -1) {
                        if (playerClient.get(target.getName()) == EnumPlayerClient.COMMAND) {
                            target.sendMessage("[MoneySJobs] 終了しました。");
                        } else {
                            SharingData.phones.get(target.getName()).open();
                        }
                        playerClient.remove(target.getName());
                        return;
                    }
                    switch (form.getResponse().getClickedButtonId()) {
                        case 0:
                            sendJobList(target);
                            break;
                        case 1:
                            sendJobStatus(target);
                            break;
                    }
                });
    }

    public void sendJobList(Player player) {
        new SimpleForm()
                .setTitle("ジョブ選択 - ジョブシステム")
                .setContent("変更後のジョブを選択してください。\n現在のジョブ: " + AvailableJobs.getJobById(JobDataHandler.getInstance().getJobData(player.getName()).getInt("job")).getName())
                .addButton(AvailableJobs.TREE_CUTTER.getName(), ImageType.PATH, "textures/items/wood_axe")
                .addButton(AvailableJobs.MINER.getName(), ImageType.PATH, "textures/items/stone_pickaxe")
                .addButton(AvailableJobs.FARMER.getName(), ImageType.PATH, "textures/items/gold_hoe")
                .addButton(AvailableJobs.SWORDSMAN.getName(), ImageType.PATH, "textures/items/iron_sword")
                .send(player, (target, form, data) -> {
                    if (data == -1) {
                        sendJobsHome(target);
                        return;
                    }
                    sendJobConfirm(target, AvailableJobs.getJobById(form.getResponse().getClickedButtonId()));
                });
    }

    public void sendJobConfirm(Player player, AvailableJobs job) {
        new ModalForm()
                .setTitle("条件の確認 - ジョブシステム")
                .setContent("以下の条件で転職しますか？\n\n転職後のジョブ: " + job.getName() + "\n転職費用: " + job.getCost() + MoneySAPI.UNIT + "\n!!!現在のジョブデータは消去されます！!!!")
                .setButton1(TextFormat.GREEN + "承認する")
                .setButton2(TextFormat.RED + "キャンセル")
                .send(player, (target, form, data) -> {
                    if (form.getResponse().getClickedButtonId() == 0) {
                        if (MoneySAPI.getInstance().isPayable(target, job.getCost())) {
                            JobDataHandler.getInstance().setJobData(target.getName(), job);
                            sendJobsHome(target, TextFormat.AQUA + "ジョブを変更しました！");
                        } else {
                            sendJobsHome(target, TextFormat.YELLOW + "所持金が不足しています。");
                        }
                    } else {
                        sendJobsHome(target, "変更をキャンセルしました。");
                    }
                });
    }

    public void sendJobStatus(Player player) {
        ConfigSection cs = JobDataHandler.getInstance().getJobData(player.getName());
        new SimpleForm()
                .setTitle("ステータス - ジョブシステム")
                .setContent(
                        player.getName() + "さんのステータス\n" +
                        "職種: " + AvailableJobs.getJobById(cs.getInt("job")).getName() + "\n" +
                        "経験値: " + cs.getInt("exp") + "\n" +
                        "レベル: " + cs.getInt("level") + "\n" +
                        "次のレベルまで: " + (LevelCalculator.getInstance().calcNeededExp(AvailableJobs.getJobById(cs.getInt("job")), cs.getInt("level") + 1) - cs.getInt("exp")))
                .addButton("戻る")
                .send(player, (target, form, data) -> {
                    sendJobsHome(target);
                });
    }

    private FormHandler() {
        //
    }

    public static FormHandler getInstance() {
        return instance;
    }

    public enum EnumPlayerClient {
        COMMAND(0),
        CPHONE(1);

        private final int id;

        private EnumPlayerClient(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "playerClient{" +
                    "id=" + id +
                    '}';
        }
    }
}
