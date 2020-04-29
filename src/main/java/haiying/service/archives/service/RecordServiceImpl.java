package haiying.service.archives.service;

import com.alibaba.fastjson.JSONObject;
import haiying.service.archives.domain.CardNum;
import haiying.service.archives.domain.Record;
import haiying.service.archives.mapper.RecordMapper;
import haiying.service.archives.util.DateUtil;
import io.micronaut.validation.Validated;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton
@Validated
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;

    public RecordServiceImpl(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public Record findOne(String guid) {
        return recordMapper.findOne(guid);
    }

    @Override
    public void saveRecord(Record record) {

        String date = DateUtil.GetNowDateString("yyyy-MM-dd");
        JSONObject oneCardNum = recordMapper.findOneCardNum(date);
        String returnData = "";
        if(!Objects.isNull(oneCardNum)){
            long card = Long.valueOf(oneCardNum.getString("cardNum"));
            card = card + 1;
            String newCard = Long.toString(card);
            recordMapper.updateOneCardNum(newCard, date);
            returnData = newCard;
        }else {
            String dateString = DateUtil.GetNowDateString("yyyyMMdd");
            String cardString = dateString + "000001";
            CardNum cardNum = new CardNum();
            cardNum.setCardNum(cardString).setDate(date);
            recordMapper.insertOneCardNum(cardNum);
            returnData = cardString;
        }
         record.setCardId(returnData);
         recordMapper.saveRecord(record);
    }

    @Override
    public void updateRecord(Record record) {
        recordMapper.updateRecord(record);
    }
}
