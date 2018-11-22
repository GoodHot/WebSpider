package com.goodHot.fun.spider.pipeline;

import com.goodHot.fun.domain.SpiderIndex;
import com.goodHot.fun.repository.SpiderIndexRepository;
import org.trie4j.doublearray.DoubleArray;
import org.trie4j.patricia.PatriciaTrie;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;

public abstract class BasePipeline implements Pipeline {

    protected PatriciaTrie patriciaTrie;

    protected SpiderIndex spiderIndex;

    protected SpiderIndexRepository spiderIndexRepository;

    protected DoubleArray doubleArray;

    public BasePipeline(SpiderIndexRepository spiderIndexRepository) {
        this.spiderIndexRepository = spiderIndexRepository;
    }

    protected boolean isExists(String text) {
        initTree();
        if (doubleArray == null) {
            doubleArray = new DoubleArray(this.patriciaTrie);
        }
        if (doubleArray.contains(text)) {
            return true;
        }
        this.patriciaTrie.insert(text);
        return false;
    }

    protected void initTree() {
        if (patriciaTrie == null) {
            this.spiderIndex = spiderIndexRepository.findByName(getSpiderName());
            if (this.spiderIndex == null) {
                patriciaTrie = new PatriciaTrie();
            } else {
                patriciaTrie = createTree(this.spiderIndex);
            }
        }
    }

    protected PatriciaTrie createTree(SpiderIndex spiderIndex) {
        if (spiderIndex == null || spiderIndex.getPatriciaTrie() == null) {
            return null;
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(spiderIndex.getPatriciaTrie());
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            PatriciaTrie patriciaTrie = (PatriciaTrie) in.readObject();
            return patriciaTrie;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return null;
    }

    protected void saveTree() {
        if (patriciaTrie == null || spiderIndexRepository == null) {
            return;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bts = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this.patriciaTrie);
            out.flush();
            bts = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
            }
        }
        if (spiderIndex == null) {
            spiderIndex = new SpiderIndex();
            spiderIndex.setName(getSpiderName());
        }
        spiderIndex.setPatriciaTrie(bts);
        spiderIndexRepository.save(spiderIndex);
    }

    public abstract String getSpiderName();
}
