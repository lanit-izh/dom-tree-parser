package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;

import java.io.File;
import java.util.List;

/**
 * Описывает директорию, которая содержит в себе директории блоков
 * Не содержит в себе json-файла
 */
public class BlocksDir extends DirectoryWithDirectories {

    private List<BlockDir> blockDirlist;

    public BlocksDir(File parentDir) {
        super(parentDir);
    }

    public BlocksDir(List<BlockDir> blockDirs) {
        blockDirlist = blockDirs;
    }

    public List<BlockDir> getBlockDirlist() {
        return blockDirlist;
    }

    /**
     * Добавляет общие блоки к списку блоков страницы
     * @param commonBlocks
     */
    public void addCommonBlocks(List<BlockDir> commonBlocks) {
        blockDirlist.addAll(commonBlocks);
    }

    @Override
    public void setChildDir(File parentDir) {
        blockDirlist = FileUtil.parseBlocksDir(parentDir);
    }

}
