<template>
  <div :style="`margin-top:${marginTop}`">
    <div class="log-filter">
      <a-row type="flex" align="middle">
        <a-col>
          <slot name="before"></slot>
        </a-col>

        <a-col v-if="extendBar" style="padding-left: 10px">
          <a-space>
            <a-tooltip title="清空当前缓冲区内容">
              <a-button type="primary" size="small" @click="clearLogCache"><DeleteOutlined />清空</a-button>
            </a-tooltip>
            <!-- <a-tooltip title="内容超过边界自动换行">
                  <a-switch v-model="temp.wordBreak" checked-children="自动换行" un-checked-children="不换行" @change="onChange" />
                </a-tooltip> -->
            <a-tooltip title="有新内容后是否自动滚动到底部">
              <a-switch
                v-model:checked="temp.logScroll"
                checked-children="自动滚动"
                un-checked-children="不滚动"
                @change="onChange"
              />
            </a-tooltip>
          </a-space>
        </a-col>
      </a-row>
    </div>
    <!-- <pre class="log-view" :id="`${this.id}`" :style="`height:${this.height}`">{{ defText }}</pre> -->
    <viewPre ref="viewPre" :height="height" :config="temp"></viewPre>
  </div>
</template>

<script>
import viewPre from './view-pre.vue'

export default {
  // name: 'LogView',
  components: {
    viewPre
  },
  props: {
    height: {
      type: String,
      default: '50vh'
    },
    marginTop: {
      type: String,
      default: '0'
    },
    extendBar: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      temp: {
        logScroll: true,
        // 自动换行
        wordBreak: false
      }
    }
  },
  computed: {
    regModifier() {
      return this.regModifiers.join('')
    }
  },
  mounted() {
    const cacehJson = localStorage.getItem('log-view-cache') || '{}'
    try {
      const cacheData = JSON.parse(cacehJson)
      this.temp = Object.assign({}, this.temp, cacheData)
    } catch (e) {
      console.error(e)
    }
  },
  methods: {
    appendLine(data) {
      this.$refs.viewPre.appendLine(data)
    },
    clearLogCache() {
      this.$refs.viewPre.clearLogCache()
    },
    onChange() {
      localStorage.setItem('log-view-cache', JSON.stringify(this.temp))
    }
  }
}
</script>

<style scoped>
.log-filter {
  padding: 0 10px;
  padding-top: 0;
  padding-bottom: 10px;
  line-height: 0;
}
:deep(.ant-checkbox-group-item) {
  display: flex;
  align-items: center;
}
</style>
