package disk;

import java.io.Serializable;

/**
 * FAT表记录磁盘使用情况，默认磁盘空间足够
 * @author 风信子
 */
public class Fat implements Serializable{

	//文件分配表
	public int[] fat;

	public Fat(){
		//初始化FAT表:0,1,2,3为FAT占用，4为根目录占用
		fat = new int[256];
		for (int i = 0; i < 5; i++) {
			fat[i] = -1;
		}
		for (int i = 5; i < fat.length; i++) {
			fat[i] = 0;
		}
	}

	/**
	 * 从头遍历，获取空闲盘块号
	 * @return 5-255的磁盘块号
	 */
	private int seekFreeBlockNum(){
		int index;
		for (index = 0; index < fat.length; index++) {
			if (fat[index] == 0) {
				break;
			}
		}
		return index;
	}

	/**
	 *由于文件大小的改变来改变FAT表的内容，
	 *用于TxtTile,ExeFile文本内容改变时
	 * @param size 文件大小
	 * @param originNum 首磁盘块号
	 */
	public void changeByTextSize(int size, int originNum){
		//该文件已经使用的磁盘块数量
		int usedNum = 1;
		int index = originNum;
		while (fat[index] != -1) {
			usedNum ++;
			index = fat[index];
		}
		//记录原本的最后一个磁盘块
		int last = index;
		//更改文本内容后所需要的磁盘块数量
		int nowNum = (size + 63) / 64;

		if (usedNum > nowNum) {  //原本所占用的磁盘块较多，则选取前部分
			index = originNum;
			for (int i = 1; i < nowNum; ++i){
				index = fat[index];
			}
			int next = fat[index];
			fat[index] = -1;
			while (fat[next] != -1) {
				index = fat[next];
				fat[next] = 0;
				next = index;
			}
			fat[next] = 0;
		}else if (usedNum == nowNum) { //所占用磁盘块数不变，无须操作

		}else {  //现需要更多的磁盘块，寻找空的盘块
			int need = nowNum - usedNum;
			for (int i = 0; i < need; ++i){
				fat[last] = seekFreeBlockNum();
				last = fat[last];
				fat[last] = -1;
			}
		}

	}

	/**
	 * 由于新建文件改变FAT表的内容
	 * @return 该新建文件的首磁盘块号
	 */
	public int changeByFileCreate(){
		int index = seekFreeBlockNum();
		fat[index] = -1;
		return index;
	}

	public int changeByFileCopy(int size){
		int nowNum = (size + 63) / 64;
		int[] request = new int[nowNum];
		int allow = 0;
		for (int i = 5; i < fat.length && allow < nowNum; ++i){
			if (fat[i] == 0) {
				request[allow++] = i;
			}
		}
		for (int i = 0; i < request.length-1; i++) {
			fat[request[i]] = request[i+1];
		}
		fat[request[nowNum-1]] = -1;
//		for (int i = 0; i < request.length; i++) {
//			System.out.print(request[i]+" ");
//		}
		return request[0];
	}

	/**
	 * 当删除文件时，释放磁盘空间，改变FAT表
	 * @param originNum 首磁盘块号
	 */
	public void release(int originNum){
		int index = originNum;
		while (fat[index] != -1) {
			int i = fat[index];
			fat[index] = 0;
			index = i;
		}
		fat[index] = 0;
	}

	/**
	 * 获取FAT表
	 * @return
	 */
	public int[] getFat(){
		return fat;
	}

	public void printFat(){
		for (int i = 0; i < 30; i++) {
			System.out.print(fat[i] + " ");
		}
		System.out.println();
	}
}
