# MARS_GUI

这玩意除了被某个神仙改好看之外，现在还可以直接dump给vivado block memory用的COE和uart下载用的文件了

默认大小是text write depth 8192，data write depth 4096，需要改的去`mars.mips.dump`包对应的地方改两个数就行。

dump之前需要去settings-memory conf里把选项改成compact, text at address 0。

受mars的限制，不支持同时把.data导出在0x0，需要导出时换选项。

## Version 1.1 更新

增加了文件自动重载功能，检测到外部修改时，Mars会自动重载所有打开且没有未保存修改的文件(若要关闭，请取消勾选editor选项里的Auto-Reload选项)
>目前重载从Mars新创建的文件会抛出异常无限重启reload线程，~~不准备~~懒得修了，建议不要在Mars里面创建文件，否则你可能需要重启mars来恢复自动重载功能(

增加了shell脚本用于Linux下打包和运行
