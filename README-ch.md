### 总结一下你的工作流



java 17->java 21 , docker 能够运行，21，需要注意：如果是本地开发测试，1.Docker 容器默认无法直接访问宿主机的 localhost，直接用 方法一 或 方法四 的 host.docker.internal 方式最方便，Dockerfile 中的 ENV 只是默认值，运行时的 -e 会覆盖它。2.Windows CMD 不支持 \ 换行符，需要改成一行或者使用其他方式。3.动态数据源（dynamic-datasource），它的配置方式与普通 Spring Boot 不同。你的环境变量 SPRING_DATASOURCE_URL 对动态数据源不生效，因为动态数据源读取的是 spring.datasource.dynamic.datasource.master.url  url: ${DB_URL:jdbc:postgresql://localhost:5432/ry_bob?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true&stringtype=unspecified}4.CMD 中 & 是特殊字符（命令分隔符），上面的命令可能会被截断。
解决方案：给 URL 加双引号  或者使用 ^ 转义：docker run -d --name ruoyi-server -p 8080:8080 -e DB_URL=jdbc:postgresql://host.docker.internal:5432/ry_bob?useUnicode=true^&characterEncoding=utf8^&useSSL=true^&autoReconnect=true^&reWriteBatchedInserts=true^&stringtype=unspecified -e DB_USERNAME=postgres -e DB_PASSWORD=123456 -e SPRING_REDIS_HOST=host.docker.internal ruoyi/ruoyi-server:5.5.3



docker run -d --name ruoyi-server -p 8080:8080 -e DB_URL=jdbc:postgresql://host.docker.internal:5432/ry_bob?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true&stringtype=unspecified -e DB_USERNAME=postgres -e DB_PASSWORD=123456 -e SPRING_REDIS_HOST=host.docker.internal ruoyi/ruoyi-server:5.5.3

docker run -d --name ruoyi-server -p 8080:8080 -e DB_URL=jdbc:postgresql://host.docker.internal:5432/ry_bob?useUnicode=true^&characterEncoding=utf8^&useSSL=true^&autoReconnect=true^&reWriteBatchedInserts=true^&stringtype=unspecified -e DB_USERNAME=postgres -e DB_PASSWORD=123456 -e SPRING_REDIS_HOST=host.docker.internal ruoyi/ruoyi-server:5.5.3


建议的完整操作流程如下：

1. 配置上游仓库 (只需做一次)

在项目文件夹右键 → TortoiseGit → Settings → Git → Remote。
点击 Add New，填入：
Remote: 建议命名为 upstream
URL: 填写原始项目的地址 (https://github.com/JavaLionLi/plus-ui.git)
点击 Add / OK 保存。
2. 更新本地 main 分支

切换到本地 main 分支。
右键 → TortoiseGit → Pull。
关键步骤： 在弹出的窗口中，Remote 一定要选择刚才添加的 upstream（不要选 origin），Branch 选择 main。
点击 OK。这样原作者的最新代码就到了你本地的 main 分支。
3. 合并到 dev 分支

切换到本地 dev 分支。
右键 → TortoiseGit → Merge...
在“合并自”的分支中选择本地的 main 分支。
点击 OK。如果有冲突，解决冲突后提交即可。
总结一下：
你说的“切到 main -> pull -> 切到 dev -> merge” 这个顺序完全正确，核心区别在于Pull 的时候必须选对上游仓库，否则就是“自己同步自己”，代码不会更新。


### 

<table data-v-5c5bdb04=""><thead data-v-5c5bdb04=""><tr data-v-5c5bdb04=""><th data-v-5c5bdb04="" align="left">特性</th><th data-v-5c5bdb04="" align="left">直接翻译</th><th data-v-5c5bdb04="" align="left">映射翻译</th></tr></thead> <tbody data-v-5c5bdb04=""><tr data-v-5c5bdb04=""><td data-v-5c5bdb04="" align="left" class=""><strong data-v-5c5bdb04="">mapper 属性</strong></td><td data-v-5c5bdb04="" align="left" class="">不设置或为空</td><td data-v-5c5bdb04="" align="left" class="">必须设置为另一个字段名</td></tr><tr data-v-5c5bdb04=""><td data-v-5c5bdb04="" align="left" class=""><strong data-v-5c5bdb04="" class="">数据来源</strong></td><td data-v-5c5bdb04="" align="left" class="">当前字段自身的值</td><td data-v-5c5bdb04="" align="left" class=""><code data-v-782faaad="" data-v-7bd7ca31="" class="segment-code-inline">mapper</code> 指定字段的值</td></tr><tr data-v-5c5bdb04=""><td data-v-5c5bdb04="" align="left" class=""><strong data-v-5c5bdb04="" class="">字段用途</strong></td><td data-v-5c5bdb04="" align="left" class="">既存原始值，又存翻译结果（覆盖）</td><td data-v-5c5bdb04="" align="left" class="">原始值和翻译结果分开存储</td></tr><tr data-v-5c5bdb04=""><td data-v-5c5bdb04="" align="left" class=""><strong data-v-5c5bdb04="" class="">典型场景</strong></td><td data-v-5c5bdb04="" align="left" class="">字典转换、OSS ID 转 URL</td><td data-v-5c5bdb04="" align="left" class="">ID 转名称（保留 ID 字段）</td></tr><tr data-v-5c5bdb04=""><td data-v-5c5bdb04="" align="left" class=""><strong data-v-5c5bdb04="" class="">字段命名</strong></td><td data-v-5c5bdb04="" align="left" class="">通常保持原意（如 <code data-v-782faaad="" data-v-7bd7ca31="" class="segment-code-inline">status</code> → <code data-v-782faaad="" data-v-7bd7ca31="" class="segment-code-inline">status</code>）</td><td data-v-5c5bdb04="" align="left" class="">通常是 <code data-v-782faaad="" data-v-7bd7ca31="" class="segment-code-inline">xxxName</code>、<code data-v-782faaad="" data-v-7bd7ca31="" class="segment-code-inline">xxxLabel</code> 形式</td></tr></tbody></table>




完成以上设置后，你的日常开发就非常方便了：
*   **开发新功能**：始终在 `dev` 分支上提交代码。
*   **同步上游更新**：
    1.  切换到 `main` 分支（右键 -> TortoiseGit -> Switch/Checkout...）。Pull 操作基本等同于 Fetch + Merge 这两步操作 一下2，3可以用拉取Pull替代，
    2.  右键 -> TortoiseGit -> **Fetch**。在弹出的窗口中，确保Remote选的是 `upstream`，然后点击OK。这会拉取项目A的最新代码到本地，但不会合并。
    3.  再次右键 -> TortoiseGit -> **Merge...**。合并 `upstream/main` 到本地的 `main` 分支。
    4.  切换回 `dev` 分支。
    5.  右键 -> TortoiseGit -> **Merge...**。将本地的 `main` 分支合并到 `dev` 分支。这样，`dev` 分支就既包含了你的修改，也包含了上游的最新更新。
        这个流程用TortoiseGit完全可以实现，而且图形化操作让每一步都很清晰。刚开始可能觉得步骤多，但设置一次之后就一劳永逸了。祝你开发顺利！




这种情况在开源项目二次开发中非常常见，一个明智的Git工作流能让你事半功倍。结合你的需求，推荐采用“主分支同步，开发分支隔离”的策略：
### 推荐的分支结构
1.  **`main` 分支**：这个分支的唯一职责就是**跟踪上游项目A的更新**。它应该是干净的，除了合并上游代码外，不应该有任何你自己的提交。
2.  **`dev` 分支**：这是你的**主开发分支**。基于 `main` 分支创建，你所有的定制开发都在这个分支上进行。
3.  **功能分支（可选但推荐）**：当你需要开发一个比较大的新功能时，可以从 `dev` 分支再创建一个 `feature/xxx` 分支。开发完成后，再合并回 `dev`。这样可以让 `dev` 分支始终保持相对稳定。
### 具体操作流程
#### 初始设置
1.  **Fork并克隆**：你已经Fork了项目A到自己的仓库B，并克隆到本地。
2.  **添加上游仓库**：在本地仓库中，把原始项目A的仓库地址添加为“上游仓库”。
    ```bash
    git remote add upstream <项目A的原始仓库地址>
    ```
3.  **创建分支**：确保你本地的 `main` 分支和远程的 `main` 分支一致，然后创建并切换到 `dev` 分支。
    ```bash
    git checkout -b dev
    ```
#### 日常开发工作流
1.  **开发新功能**：始终在你的 `dev` 分支上进行编码和提交。
    ```bash
    git checkout dev
    # ...进行修改...
    git add .
    git commit -m "feat: 添加了我的定制功能"
    ```
2.  **定期同步上游更新**：这是最关键的一步，可以让你方便地获取项目A的更新。
    ```bash
    # 1. 切换到 main 分支
    git checkout main
    # 2. 拉取上游仓库的最新代码
    git fetch upstream
    git merge upstream/main  # 或者使用 git rebase upstream/main，取决于你的偏好
    # 3. 将更新后的 main 分支合并到你的 dev 分支
    git checkout dev
    git merge main
    ```
    *   **合并时可能会有冲突**，因为项目A的代码和你的修改可能在同一个文件上。解决冲突是这个过程的核心。好在你说“别人的项目A代码又很好合并”，这会让过程顺利很多。
3.  **发布或备份**：当你觉得 `dev` 分支上的代码稳定后，可以将其推送到你自己的GitHub仓库B。
    ```bash
    git push origin dev
    ```
### 为什么这样做是明智的？
*   **清晰的职责分离**：`main` 分支是“纯净水”，只管同步上游；`dev` 分支是你的“工作台”，所有改动都在这里。互不干扰。
*   **合并过程可控**：你可以选择在任何你认为合适的时间点，将上游更新合并到你的开发分支中。如果一次合并冲突太多，你甚至可以分批次、分阶段地处理。
*   **易于回滚**：如果某次合并上游代码后，出现了意想不到的问题，你可以轻松地回退到合并前的状态。
*   **历史记录干净**：你的开发历史和上游项目的更新历史是分开的，非常清晰，便于追踪问题和回溯。
    这个工作流既保证了你能跟上上游的步伐，又给了你充分的自由进行定制开发，是处理此类问题的标准最佳实践。

--------------------------------
以上内容由AI生成，仅供参考和借鉴


完全可以！用TortoiseGit的图形界面来操作，整个过程会更直观。既然你还没有创建B项目和拉取代码，那正好从零开始，流程非常清晰。
下面我给你一个完整的、一步一步的TortoiseGit操作指南：
---
### 第一步：在GitHub上Fork项目A（创建你的项目B）
这一步是在GitHub网站上完成的，不是在TortoiseGit里。
1.  打开浏览器，访问项目A的地址：`https://github.com/dromara/RuoYi-Vue-Plus.git`
2.  点击页面右上角的 **Fork** 按钮。
3.  在弹出的窗口中，确认你的GitHub账户（`bobgit`）被选中，然后点击绿色的 **Create fork** 按钮。
4.  稍等片刻，GitHub就会为你创建一个一模一样的项目，地址就是 `https://github.com/bobgit/RuoYi-Vue-Plus.git`。这就是你的项目B。
    **至此，项目B已经存在了，但它现在只是项目A的一个快照。**
---
### 第二步：将你的项目B克隆到本地
现在我们要把刚刚创建的项目B下载到你的电脑上。
1.  在你的电脑上创建一个空文件夹，比如 `D:\Projects\RuoYi-Vue-Plus`。
2.  进入这个文件夹，在空白处右键，选择 **TortoiseGit** -> **Clone...**。
3.  在弹出的窗口中填写：
    *   **URL:** `https://github.com/bobgit/RuoYi-Vue-Plus.git` （你刚刚Fork的项目B的地址）
    *   **Directory:** 会自动填充为你刚才创建的文件夹路径，保持默认即可。
4.  点击 **OK**。TortoiseGit就会开始下载代码到本地。
    完成后，你的本地就有了一个完整的RuoYi-Vue-Plus项目，并且它已经和你在GitHub上的项目B（`origin`）关联好了。
---
### 第三步：添加上游仓库（项目A）到本地
这是最关键的一步！我们需要告诉本地的Git仓库，除了你自己的项目B之外，还有一个“上游”的项目A需要跟踪更新。
1.  在刚才克隆下来的项目文件夹里，再次右键，选择 **TortoiseGit** -> **Settings...**。
2.  在设置窗口的左侧，点击 **Git** -> **Remote**。
3.  在右侧，你会看到已经有一个名为 `origin` 的远程仓库，指向你自己的项目B。现在点击 **Add New...** 按钮。
4.  在弹出的窗口中填写：
    *   **Remote:** `upstream` （这个名字是约定俗成的，代表上游仓库）
    *   **URL:** `https://github.com/dromara/RuoYi-Vue-Plus.git` （原始项目A的地址）
    *   **Putty Key:** 留空（因为你用的是HTTPS地址）
5.  点击 **Add**，然后点击 **OK** 保存设置。
    现在，你的本地仓库就有了两个“邻居”：
*   `origin`: 你自己的GitHub仓库 (`bobgit/RuoYi-Vue-Plus`)
*   `upstream`: 原始作者的GitHub仓库 (`dromara/RuoYi-Vue-Plus`)
---
### 第四步：创建你的开发分支 `dev`
按照我们之前讨论的策略，`main` 分支保持干净，专门用来同步上游代码。我们自己的开发在 `dev` 分支上进行。
1.  在项目文件夹里右键，选择 **TortoiseGit** -> **Create Branch...**。
2.  在弹出的窗口中：
    *   **Branch:** 填写 `dev`。
    *   **Base on:** 确保选择的是 `main` 或 `master`（取决于项目的默认分支名）。
    *   勾选 **Switch to new branch** （创建后立即切换到这个分支）。
3.  点击 **OK**。
    现在你就处于 `dev` 分支了，可以在这里放心地进行你的定制开发，不会影响到 `main` 分支。
---
### 第五步：将新分支推送到GitHub
最后一步，把本地的 `dev` 分支也推送到你的项目B中，这样你在GitHub上也能看到它，并且可以作为备份。
1.  在项目文件夹里右键，选择 **TortoiseGit** -> **Push...**。
2.  在Push窗口中，Destination会自动选择 `origin`。
3.  你会看到本地 `dev` 分支旁边有一个绿色的箭头，表示它将被推送到远程。确保 **"Set upstream tracking branch"** 选项是勾选的。
4.  点击 **OK**。
    现在去你的GitHub项目B页面看看，是不是已经有 `main` 和 `dev` 两个分支了？
---
### 总结一下你的工作流
完成以上设置后，你的日常开发就非常方便了：
*   **开发新功能**：始终在 `dev` 分支上提交代码。
*   **同步上游更新**：
    1.  切换到 `main` 分支（右键 -> TortoiseGit -> Switch/Checkout...）。
    2.  右键 -> TortoiseGit -> **Fetch**。在弹出的窗口中，确保Remote选的是 `upstream`，然后点击OK。这会拉取项目A的最新代码到本地，但不会合并。
    3.  再次右键 -> TortoiseGit -> **Merge...**。合并 `upstream/main` 到本地的 `main` 分支。
    4.  切换回 `dev` 分支。
    5.  右键 -> TortoiseGit -> **Merge...**。将本地的 `main` 分支合并到 `dev` 分支。这样，`dev` 分支就既包含了你的修改，也包含了上游的最新更新。
        这个流程用TortoiseGit完全可以实现，而且图形化操作让每一步都很清晰。刚开始可能觉得步骤多，但设置一次之后就一劳永逸了。祝你开发顺利！

--------------------------------
以上内容由AI生成，仅供参考和借鉴



最小可用模板（Spring Boot 3 + JDK 21）
java
###
@Bean
public TomcatProtocolHandlerCustomizer<?> virtualThreadProtocolHandler() {
return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
}
把 Tomcat 的 IO 线程直接换成虚拟线程，QPS 轻松翻倍（前提是业务代码里不再有 synchronized）。

对应的代码生成，要注意 org.dromara.system.controller.SysOrgController  org.dromara.system.controller.SysOrgController;  org.dromara.ecom.controller;

2026-02-11
postgres_ecom.sql   添加
结构添加，对应的表格都可以新增及修改



