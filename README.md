# MARS_GUI

这玩意除了被某个神仙改好看之外，现在还可以直接dump给vivado block memory用的COE和uart下载用的文件了

默认大小是text write depth 8192，data write depth 4096，需要改的去`mars.mips.dump`包对应的地方改两个数就行。

dump之前需要去settings-memory conf里把选项改成compact, text at address 0。

受mars的限制，不支持同时把.data导出在0x0，需要导出时换选项。
