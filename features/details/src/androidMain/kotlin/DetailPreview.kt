import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.detail.DetailScreen
import com.example.detail.DetailUI
import com.example.detail.DetailViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Preview(showBackground = true, device = Devices.PIXEL_6)
@Composable
fun PreviewDetailScreen() {
    MaterialTheme {
        DetailScreen(
            detailViewModel = object : DetailViewModel {
                override val sampleDataFlow: Flow<DetailUI>
                    get() = flowOf(DetailUI("Name 1", "Description 1"),)
            },
            onBackAction = {}
        )
    }
}