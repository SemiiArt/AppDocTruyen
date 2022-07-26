package com.tuyenhoang.appdoctruyenjsoupv2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.tuyenhoang.appdoctruyenjsoupv2.ItemStory
import com.tuyenhoang.appdoctruyenjsoupv2.R
import com.tuyenhoang.appdoctruyenjsoupv2.Story
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ActivityStoryBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class StoryActivity : AppCompatActivity() {
    private var listContentStory= mutableListOf<Story>()
    private lateinit var binding:ActivityStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_story)
        val itemStory:ItemStory=intent.getSerializableExtra("itemStoryData") as ItemStory
        binding.storyName.text=itemStory.title
        Glide.with(applicationContext)
            .load(itemStory.linkImage)
            .error(R.drawable.img)
            .placeholder(R.drawable.img)
            .into(binding.storyImage)
        readData(itemStory.linkHtml)
    }

    private fun readData(linkHtml:String) {
        thread {
            val doc=Jsoup.connect(linkHtml).get()
            val listConten=doc.getElementsByClass("entry-content")
            val list=listConten[0].getElementsByTag("p")
            for (lists in list){
                val content = lists.text()
                listContentStory.add(Story(content))
            }
            this.runOnUiThread {
                var string=String()
                var i=1
                while (i<listContentStory.size){
                    string=string.plus("\n").plus(listContentStory[i].content)
                    i++
                }
                binding.storyContent.text=string
            }
        }
    }
}