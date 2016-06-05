package me.ahmed.batch.writer;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import me.ahmed.batch.model.User;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class UserWriter implements ItemWriter<User>, FlatFileFooterCallback,
		FlatFileHeaderCallback {

	private ItemWriter<User> delegate;

	// private BigDecimal totalAmount = BigDecimal.ZERO;

	private int recordCount = 0;

	public void writeHeader(Writer writer) throws IOException {
		writer.write("output_file.txt" + "," + new Date());
	}

	public void write(List<? extends User> items) throws Exception {
		// BigDecimal chunkTotal = BigDecimal.ZERO;
		int chunkRecord = 0;
		System.out.println("======================================> "
				+ items.size());
		for (User user : items) {
			chunkRecord++;
			// chunkTotal = chunkTotal.add(new BigDecimal(user.getVersion()));
		}
		recordCount += chunkRecord;
		delegate.write(items);
		// After successfully writing all items
		// totalAmount = totalAmount.add(chunkTotal);

	}

	public void writeFooter(Writer writer) throws IOException {
		System.out.println("recordCount from writeFooter  : " + recordCount);
		writer.write("=====>" + recordCount);
	}

	public void setDelegate(ItemWriter<User> delegate) {
		this.delegate = delegate;
	}

//	public void close() throws ItemStreamException {
//		System.out.println("recordCount from close  : " + recordCount);
//		this.delegate.close();
//	}
//
//	public void open(ExecutionContext arg0) throws ItemStreamException {
//		System.out.println("recordCount from open  : " + recordCount);
//		this.delegate.open(arg0);
//	}
//
//	public void update(ExecutionContext arg0) throws ItemStreamException {
//		System.out.println("recordCount from update  : " + recordCount);
//		this.delegate.update(arg0);
//	}
}