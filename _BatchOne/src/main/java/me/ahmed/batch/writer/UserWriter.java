package me.ahmed.batch.writer;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;

import me.ahmed.batch.model.User;

public class UserWriter implements ItemWriter<User>, FlatFileFooterCallback, FlatFileHeaderCallback, ItemStream {

	private FlatFileItemWriter<User> delegate;

//	private BigDecimal totalAmount = BigDecimal.ZERO;

	private int recordCount = 0;

	public void writeHeader(Writer writer) throws IOException {
		writer.write("output_file.txt" + "," + new Date());
	}

	@Override
	public void write(List<? extends User> items) throws Exception {
//		BigDecimal chunkTotal = BigDecimal.ZERO;
		int chunkRecord = 0;
		System.out.println("======================================> "+items.size());
		for (User user : items) {
			chunkRecord++;
//			chunkTotal = chunkTotal.add(new BigDecimal(user.getVersion()));
		}
		delegate.write(items);
		// After successfully writing all items
//		totalAmount = totalAmount.add(chunkTotal);
		recordCount += chunkRecord;
	}
	
	public void writeFooter(Writer writer) throws IOException {
		writer.write("=====>" + recordCount);
	}

	public void setDelegate(FlatFileItemWriter<User> delegate) {
		this.delegate = delegate;
	}

	public void close() throws ItemStreamException {
		this.delegate.close();
	}

	public void open(ExecutionContext arg0) throws ItemStreamException {
		this.delegate.open(arg0);
	}

	public void update(ExecutionContext arg0) throws ItemStreamException {
		this.delegate.update(arg0);
	}
}