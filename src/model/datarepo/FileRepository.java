package model.datarepo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Implementation responsible for reading/writing the data to data source. File Repository
 * uses file as the data source to read data to and load data from.
 */
public class FileRepository implements DataRepository<String> {

  // the path of the File that servers as data source.
  private Path path;

  private static final DataRepository instance = new FileRepository();


  public static DataRepository getInstance() {
    return instance;
  }

  public FileRepository() {
    this.path = null;
  }

  @Override
  public void setDataSource(String dataSource) throws Exception {
    Path sentPath = Paths.get(dataSource);
    boolean isAbsolute = sentPath.isAbsolute();

    this.path = isAbsolute ?  sentPath :
            Paths.get(new File(".").getCanonicalPath(), dataSource).toAbsolutePath();
    File file = new File(dataSource);
    if (!file.exists()) {
      Files.createDirectories(this.path.getParent());
      file.createNewFile();
    }
    Files.newInputStream(this.path, StandardOpenOption.CREATE_NEW);
  }

  @Override
  public void save(String trade) throws Exception {
    // appends the data to the file(i.e. data source).
    Files.writeString(path, trade, StandardOpenOption.APPEND);
  }

  @Override
  public String read() throws Exception {
    // reads the data as string and returns the result.
    String result = Files.readString(path);
    return result;
  }
}