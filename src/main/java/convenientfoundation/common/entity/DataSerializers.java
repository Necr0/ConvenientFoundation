package convenientfoundation.common.entity;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSerializers {
	public static boolean initialized=false;

	public static final DataSerializer<List<String>> LISTSTRING=new DataSerializer<List<String>>(){
		@Override
		public void write(PacketBuffer buf, List<String> value) {
			buf.writeInt(value.size());
			for(String s:value){
				buf.writeString(s);
			}
		}

		@Override
		public List<String> read(PacketBuffer buf) throws IOException {
			List<String> list=new ArrayList<>();
			int count=buf.readInt();
			for(int i=0;i<count;i++){
				list.add(buf.readString(64));
			}
			return list;
		}

		@Override
		public DataParameter<List<String>> createKey(int id) {
			return new DataParameter<>(id, this);
		}

		@Override
		public List<String> copyValue(List<String> value) {
			List<String> ret=new ArrayList<>();
			for(String s:value){
				ret.add(s);
			}
			return ret;
		}
	};

	public static void initSerializers(){
		if(initialized)
			return;
		net.minecraft.network.datasync.DataSerializers.registerSerializer(LISTSTRING);
		initialized=true;
	}
}
