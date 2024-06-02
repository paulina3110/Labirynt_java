public class PlikBinarny {

    private int fileId;
    private byte escape;
    private short columns;
    private short lines;
    private short entryX;
    private short entryY;
    private short exitX;
    private short exitY;
    private byte [] reserved;
    private int counter;
    private int solutionOffset;
    private byte separator;
    private byte wall;
    private byte path;

    private short steps;

    PlikBinarny(){
        reserved = new byte[12];
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setEscape(byte escape) {
        this.escape = escape;
    }

    public void setColumns(short columns) {
        this.columns = columns;
    }

    public void setLines(short lines) {
        this.lines = lines;
    }

    public void setEntryX(short entryX) {
        this.entryX = entryX;
    }

    public void setEntryY(short entryY) {
        this.entryY = entryY;
    }

    public void setExitX(short exitX) {
        this.exitX = exitX;
    }

    public void setExitY(short exitY) {
        this.exitY = exitY;
    }

    public void setRes(byte[] res) {
        this.reserved = res;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setSolutionOffset(int solutionOffset) {
        this.solutionOffset = solutionOffset;
    }

    public void setSeparator(byte separator) {
        this.separator = separator;
    }

    public void setWall(byte wall) {
        this.wall = wall;
    }

    public void setPath(byte path) {
        this.path = path;
    }


    public void setSteps(short steps) {
        this.steps = steps;
    }

    public int getFileId() {
        return fileId;
    }

    public byte getEscape() {
        return escape;
    }

    public short getColumns() {
        return columns;
    }

    public short getLines() {
        return lines;
    }

    public short getEntryX() {
        return entryX;
    }

    public short getEntryY() {
        return entryY;
    }

    public short getExitX() {
        return exitX;
    }

    public short getExitY() {
        return exitY;
    }

    public byte[] getRes() {
        return reserved;
    }

    public int getCounter() {
        return counter;
    }

    public int getSolutionOffset() {
        return solutionOffset;
    }

    public byte getSeparator() {
        return separator;
    }

    public byte getWall() {
        return wall;
    }

    public byte getPath() {
        return path;
    }


    public short getSteps() {
        return steps;
    }


    public void printInfo(){
        System.out.println("fileid: "+this.fileId);
        System.out.println("escape: "+ this.escape);
        System.out.println("columns: " +this.columns);
        System.out.println("lines: " +this.lines);
        System.out.println("entry_x: "+this.entryX);
        System.out.println("entryY: " + this.entryY);
        System.out.println("exit_x: " + this.exitX);
        System.out.println("exit_y: " + this.exitY);
        System.out.println("counter: " +this.counter);
        System.out.println("sollution_off: " +this.solutionOffset);
        System.out.println("separator: " +this.separator);
        System.out.println("wall: " + this.wall);
        System.out.println("path: " + this.path);
    }





}

