const API = 'http://localhost:8080';

// 사용자 관리 함수들
function createUser() {
    const name = document.getElementById('userName').value.trim();
    const email = document.getElementById('userEmail').value.trim();
    const address = document.getElementById('userAddress').value.trim();

    if (!name || !email) {
        alert('이름과 이메일은 필수 입력입니다.');
        return;
    }

    fetch(`${API}/users`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            name: name,
            email: email,
            address: address || null
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('userResult').innerText = `사용자 등록 성공: ${JSON.stringify(data, null, 2)}`;
        clearUserForm();
    })
    .catch(e => {
        document.getElementById('userResult').innerText = `오류: ${e.toString()}`;
    });
}

function fetchUsers() {
    fetch(`${API}/users`)
    .then(r => r.json())
    .then(data => {
        document.getElementById('usersList').innerText = JSON.stringify(data, null, 2);
    })
    .catch(e => {
        document.getElementById('usersList').innerText = `조회 오류: ${e.toString()}`;
    });
}

function clearUserForm() {
    document.getElementById('userName').value = '';
    document.getElementById('userEmail').value = '';
    document.getElementById('userAddress').value = '';
}


// 재고 관리 함수들
function saveInventory() {
    const productName = document.getElementById('invProductName').value.trim();
    const stock = parseInt(document.getElementById('invStock').value);

    if (!productName || isNaN(stock) || stock < 0) {
        alert('상품명과 유효한 재고수량을 입력하세요.');
        return;
    }

    fetch(`${API}/inventory`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            productName: productName,
            stock: stock
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('invResult').innerText = `재고 등록 성공: ${JSON.stringify(data, null, 2)}`;
        clearInventoryForm();
        fetchInventory(); // 목록 새로고침
    })
    .catch(e => {
        document.getElementById('invResult').innerText = `오류: ${e.toString()}`;
    });
}

function updateInventory() {
    const inventoryId = document.getElementById('invId').value;
    const productName = document.getElementById('invProductName').value.trim();
    const stock = parseInt(document.getElementById('invStock').value);

    if (!inventoryId || !productName || isNaN(stock) || stock < 0) {
        alert('재고 ID, 상품명과 유효한 재고수량을 입력하세요.');
        return;
    }

    fetch(`${API}/inventory/${inventoryId}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            productName: productName,
            stock: stock
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('invResult').innerText = `재고 수정 성공: ${JSON.stringify(data, null, 2)}`;
        clearInventoryForm();
        fetchInventory(); // 목록 새로고침
    })
    .catch(e => {
        document.getElementById('invResult').innerText = `수정 오류: ${e.toString()}`;
    });
}

function fetchInventory() {
    fetch(`${API}/inventory`)
    .then(r => r.json())
    .then(data => {
        document.getElementById('invList').innerText = JSON.stringify(data, null, 2);
    })
    .catch(e => {
        document.getElementById('invList').innerText = `조회 오류: ${e.toString()}`;
    });
}

function enableEditMode() {
    document.getElementById('invId').style.display = 'block';
    document.getElementById('updateBtn').style.display = 'inline-block';
    document.getElementById('invResult').innerText = '수정 모드가 활성화되었습니다. 재고 ID를 입력하고 수정하세요.';
}

function clearInventoryForm() {
    document.getElementById('invId').value = '';
    document.getElementById('invProductName').value = '';
    document.getElementById('invStock').value = '';
    document.getElementById('invId').style.display = 'none';
    document.getElementById('updateBtn').style.display = 'none';
}


function createOrder() {
    const userId = parseInt(document.getElementById('orderUserId').value);
    const status = document.getElementById('orderStatus').value;
    const totalAmount = parseFloat(document.getElementById('orderAmount').value);

    if (isNaN(userId) || userId <= 0) {
        alert('유효한 사용자 ID를 입력하세요.');
        return;
    }

    fetch(`${API}/orders`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            userId: userId,
            status: status,
            orderDate: new Date().toISOString(),
            totalAmount: isNaN(totalAmount) ? 0.0 : totalAmount
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('orderResult').innerText = `주문 생성 성공: ${JSON.stringify(data, null, 2)}`;
        clearOrderForm();
    })
    .catch(e => {
        document.getElementById('orderResult').innerText = `오류: ${e.toString()}`;
    });
}

function fetchOrders() {
    fetch(`${API}/orders`)
    .then(r => r.json())
    .then(data => {
        document.getElementById('ordersList').innerText = JSON.stringify(data, null, 2);
    })
    .catch(e => {
        document.getElementById('ordersList').innerText = `조회 오류: ${e.toString()}`;
    });
}

function clearOrderForm() {
    document.getElementById('orderUserId').value = '';
    document.getElementById('orderStatus').value = 'PENDING';
    document.getElementById('orderAmount').value = '';
}

function addOrderItem() {
    const orderId = parseInt(document.getElementById('itemOrderId').value);
    const productName = document.getElementById('itemProductName').value.trim();
    const quantity = parseInt(document.getElementById('itemQuantity').value);
    const unitPrice = parseFloat(document.getElementById('itemUnitPrice').value);

    if (isNaN(orderId) || !productName || isNaN(quantity) || isNaN(unitPrice) ||
        orderId <= 0 || quantity <= 0 || unitPrice < 0) {
        alert('모든 필드를 올바르게 입력하세요.');
        return;
    }

    const totalPrice = quantity * unitPrice;

    fetch(`${API}/order-items`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            orderId: orderId,
            productName: productName,
            quantity: quantity,
            unitPrice: unitPrice,
            totalPrice: totalPrice
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('itemResult').innerText = `주문항목 추가 성공: ${JSON.stringify(data, null, 2)}`;
        clearOrderItemForm();
    })
    .catch(e => {
        document.getElementById('itemResult').innerText = `오류: ${e.toString()}`;
    });
}

function fetchOrderItems() {
    fetch(`${API}/order-items`)
    .then(r => r.json())
    .then(data => {
        document.getElementById('itemsList').innerText = JSON.stringify(data, null, 2);
    })
    .catch(e => {
        document.getElementById('itemsList').innerText = `조회 오류: ${e.toString()}`;
    });
}

function clearOrderItemForm() {
    document.getElementById('itemOrderId').value = '';
    document.getElementById('itemProductName').value = '';
    document.getElementById('itemQuantity').value = '';
    document.getElementById('itemUnitPrice').value = '';
}

function enableEditMode() {
    document.getElementById('invId').style.display = 'block';
    document.getElementById('updateBtn').style.display = 'inline-block';
    document.getElementById('invResult').innerText = '수정 모드가 활성화되었습니다. 재고 ID를 입력하고 수정하세요.';
}

function completeOrder() {
    const id = parseInt(document.getElementById('completeOrderId').value);
    if (!id) return alert('주문 ID를 입력하세요.');

    fetch(`${API}/orders/${id}/complete`, { method: 'PUT' })
      .then(res => res.ok ? res.json() : Promise.reject(res.statusText))
      .then(order => {
         alert(`주문 ${order.id} 완료: 재고 자동 차감됨`);
         fetchInventory();
         fetchOrders();
      })
      .catch(err => alert('주문 완료 실패: ' + err));
}

let statusModeOn = false;

function toggleStatusMode() {
  statusModeOn = !statusModeOn;
  document.getElementById('statusMode').style.display = statusModeOn ? 'block' : 'none';
  document.getElementById('toggleStatusModeBtn').innerText =
    statusModeOn ? '상태 변경 모드 종료' : '상태 변경 모드 토글';
}

function changeOrderStatus() {
  const id = parseInt(document.getElementById('statusOrderId').value);
  const status = document.getElementById('newStatus').value;
  if (!id) {
    return alert('유효한 주문 ID를 입력하세요.');
  }
  fetch(`${API}/orders/${id}/status?status=${status}`, {
    method: 'PUT'
  })
    .then(async res => {
      if (!res.ok) throw new Error(await res.text() || '상태 변경 실패');
      return res.json();
    })
    .then(order => {
      document.getElementById('statusResult').innerText =
        `주문 ${order.id} 상태가 ${order.status}로 변경되었습니다.`;
      fetchOrders();  // 목록 갱신
    })
    .catch(err => {
      document.getElementById('statusResult').innerText = `오류: ${err.message}`;
    });
}

// 결제 관리 함수들
function createPayment() {
    const orderId = parseInt(document.getElementById('payOrderId').value);
    const paymentMethod = document.getElementById('payMethod').value;
    const paymentStatus = document.getElementById('payStatus').value;
    const paidAmount = parseFloat(document.getElementById('payAmount').value);

    if (isNaN(orderId) || orderId <= 0) {
        alert('유효한 주문 ID를 입력하세요.');
        return;
    }

    fetch(`${API}/payments`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            orderId: orderId,
            paymentMethod: paymentMethod,
            paymentStatus: paymentStatus,
            paidAmount: isNaN(paidAmount) ? 0.0 : paidAmount,
            paidAt: paymentStatus === 'COMPLETED' ? new Date().toISOString() : null
        })
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('payResult').innerText = `결제 생성 성공: ${JSON.stringify(data, null, 2)}`;
        clearPaymentForm();
    })
    .catch(e => {
        document.getElementById('payResult').innerText = `오류: ${e.toString()}`;
    });
}

function fetchPayments() {
    fetch(`${API}/payments`)
    .then(r => r.json())
    .then(data => {
        document.getElementById('payList').innerText = JSON.stringify(data, null, 2);
    })
    .catch(e => {
        document.getElementById('payList').innerText = `조회 오류: ${e.toString()}`;
    });
}

function clearPaymentForm() {
    document.getElementById('payOrderId').value = '';
    document.getElementById('payMethod').value = 'CARD';
    document.getElementById('payStatus').value = 'PENDING';
    document.getElementById('payAmount').value = '';
}